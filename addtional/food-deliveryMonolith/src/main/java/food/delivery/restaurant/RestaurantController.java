package food.delivery.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return  restaurantService.createRestaurant(restaurant);
    }

    @PostMapping("/{id}/menus")
    public MenuItem addMenuItem(@PathVariable Long id, @RequestBody MenuItem menu) {
        return restaurantService.addMenuItem(id, menu);
    }

    @GetMapping
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @PatchMapping("/{id}/status")
    public Restaurant updateStatus(@PathVariable Long id, @RequestParam RestaurantStatus status){
        return  restaurantService.updateStatus(id, status);
    }

}
