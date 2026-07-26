package food_delivery.order.client;

import food_delivery.order.dto.RestaurantDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "restaurant-service", url = "${restaurant.service.url:http://localhost:8081}")
public interface RestaurantClient {

    @GetMapping("/public/restaurants/{id}")
    RestaurantDto getRestaurantById(@PathVariable("id") Long id);

}
