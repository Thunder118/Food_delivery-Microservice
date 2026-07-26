package food_delivery.restaurant.repository;

import food_delivery.restaurant.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.menuItems WHERE r.id = :id")
    Optional<Restaurant> findByIdWithMenuItems(@Param("id") Long id);
}
