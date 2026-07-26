package food.delivery.restaurant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public MenuItem addMenuItem(Long restaurantId, MenuItem menu){
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restoran Not Found"));

                menu.setRestaurant(restaurant);
                return menuItemRepository.save(menu);

    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant updateStatus(Long restaurantId, RestaurantStatus status) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("cant found Restoran"));

        restaurant.setStatus(status);
        return restaurantRepository.save(restaurant);
    }

}
