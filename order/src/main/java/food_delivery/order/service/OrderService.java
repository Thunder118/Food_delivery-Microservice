package food_delivery.order.service;

import food_delivery.order.client.DriverClient;
import food_delivery.order.client.RestaurantClient;
import food_delivery.order.dto.DriverDto;
import food_delivery.order.dto.OrderCreatedEvent;
import food_delivery.order.dto.RestaurantDto;
import food_delivery.order.model.Order;
import food_delivery.order.repository.OrderRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantClient restaurantClient;
    private final DriverClient driverClient;
    private final OrderKafkaProducer orderKafkaProducer;

    @Transactional
    public Order createOrder(Order request) {

        RestaurantDto restaurants = restaurantClient.getRestaurantById(request.getRestaurantId());
        if (restaurants == null) {
            throw new IllegalArgumentException("Restaurant not found for ID: " + request.getRestaurantId());
        }


        DriverDto driver = getDriverWithCircuitBreaker(request.getDriverId());
        if (driver == null || !"AVAILABLE".equalsIgnoreCase(driver.getStatus())) {
            throw new IllegalStateException("Driver Not Available!");
        }


        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setRestaurantId(restaurants.getId());
        order.setDriverId(driver.getId() != null ? driver.getId() : request.getDriverId());
        order.setTotalAmount(request.getTotalAmount());
        order.setStatus("CREATED");

        Order savedOrder = orderRepository.save(order);


        OrderCreatedEvent event = buildOrderCreatedEvent(savedOrder);
        log.info("📤 [OrderService] Call Producer For Order ID: {}", savedOrder.getId());
        orderKafkaProducer.sendOrderCreatedEvent(event);

        return savedOrder;
    }

    @CircuitBreaker(name = "driverServiceCB", fallbackMethod = "fallbackGetDriver")
    public  DriverDto getDriverWithCircuitBreaker(Long driverId) {
        return driverClient.getDriverById(driverId);
    }

    public DriverDto fallbackGetDriver(Long driverId, Throwable throwable) {
        log.error("⚠️ [Circuit Breaker] Driver Service Down/Error! Cause: {}", throwable.getMessage());

        DriverDto fallbackDriver = new DriverDto();
        fallbackDriver.setId(driverId);
        fallbackDriver.setName("UNKNOWN_DRIVER");
        fallbackDriver.setName("SERVICE_DOWN");
        return fallbackDriver;
    }

    @Cacheable(value = "order", key = "#id")
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order Not Found for ID: " + id));
    }

    private OrderCreatedEvent buildOrderCreatedEvent(Order order) {
        return OrderCreatedEvent.builder()
                .orderId(order.getId())
                .customerId(order.getCustomerId())
                .restaurantId(order.getRestaurantId())
                .driverId(order.getDriverId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .build();
    }
}
