package food_delivery.restaurant.controller;

import food_delivery.restaurant.model.MenuItem;
import food_delivery.restaurant.model.Restaurant;
import food_delivery.restaurant.model.RestaurantStatus;
import food_delivery.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/public/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RedisCacheManager cacheManager;

    @PostMapping
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return  restaurantService.createRestaurant(restaurant);
    }

    @PostMapping("/{id}/menus")
    public MenuItem addMenuItem(@PathVariable Long id, @RequestBody MenuItem menu) {
        return restaurantService.addMenuItem(id, menu);
    }

    @GetMapping("/{id}")
    public Restaurant getRestaurantById(@PathVariable Long id) {
        return restaurantService.getRestaurantById(id);
    }

    @PatchMapping("/{id}/status")
    public Restaurant updateStatus(@PathVariable Long id, @RequestParam RestaurantStatus status){
        return  restaurantService.updateStatus(id, status);
    }

    @DeleteMapping("/clear-cache")
    public  String clearCache() {
        cacheManager.getCacheNames().forEach(name -> cacheManager.getCache(name).clear());
        return "All cache in Redis success clean!";
    }

}
