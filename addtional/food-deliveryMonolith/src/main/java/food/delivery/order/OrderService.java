package food.delivery.order;

import food.delivery.driver.Driver;
import food.delivery.driver.DriverService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class OrderService {

    private final OrderRepository orderRepository;
    private final DriverService driverService;

    @Transactional
    public Order createOrder(Order order) {
        order.setStatus("PENDING");
        Order savedOrder = orderRepository.save(order);

        Driver assignedDriver = driverService.assignAvailableDriver();

        if (assignedDriver != null) {
            savedOrder.setDriverId(assignedDriver.getId());
            savedOrder.setStatus("DRIVER_ASSIGNED");
        } else {
            savedOrder.setStatus("SEARCHING_DRIVER");
        }

        return orderRepository.save(savedOrder);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

}
