package food_delivery.restaurant.service;

import food_delivery.restaurant.model.MenuItem;
import food_delivery.restaurant.model.Restaurant;
import food_delivery.restaurant.model.RestaurantStatus;
import food_delivery.restaurant.repository.MenuItemRepository;
import food_delivery.restaurant.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    public Restaurant createRestaurant(Restaurant restaurant){
        restaurant.setStatus(RestaurantStatus.CLOSED);
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurant", key = "#restaurantId")
    public MenuItem addMenuItem(Long restaurantId, MenuItem menu){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restoran Not Found"));

                menu.setRestaurant(restaurant);
                return menuItemRepository.save(menu);

    }

    @Transactional(readOnly = true)
    @Cacheable(value = "restaurant", key = "#id")
    public Restaurant getRestaurantById(Long id) {
        System.out.println(">>> [LOG DB] Mengambil data restoran ID: " + id + " dari PostgreSQL...");
        return restaurantRepository.findByIdWithMenuItems(id)
                .orElseThrow(() -> new RuntimeException("cant found restoran with Id:" + id));
    }

    @CacheEvict(value = "restaurant", key = "#id")
    public Restaurant updateRestaurant(Long id, Restaurant details) {
        Restaurant restaurant = restaurantRepository.findById(id).orElseThrow();
        restaurant.setName(details.getName());
        return restaurantRepository.save(restaurant);
    }

    @CacheEvict(value = "restaurant", key = "#id")
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

    @CacheEvict(value = "restaurant", key = "#restaurantId")
    public Restaurant updateStatus(Long restaurantId, RestaurantStatus status) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("cant found Restoran"));

        restaurant.setStatus(status);
        return restaurantRepository.save(restaurant);
    }

}
