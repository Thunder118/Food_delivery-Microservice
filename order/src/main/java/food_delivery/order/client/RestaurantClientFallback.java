package food_delivery.order.client;

import food_delivery.order.dto.RestaurantDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RestaurantClientFallback implements RestaurantClient {

    @Override
    public RestaurantDto getRestaurantById(Long id) {
        log.error("⚠️ [Circuit Breaker - Feign] Restaurant Service DOWN! Mengembalikan data fallback untuk Restaurant ID: {}", id);

        RestaurantDto fallbackRestaurant = new RestaurantDto();
        fallbackRestaurant.setId(id);
        fallbackRestaurant.setName("RESTAURANT_SERVICE_UNAVAILABLE");
        fallbackRestaurant.setAddress("N/A");
        return fallbackRestaurant;
    }
}
