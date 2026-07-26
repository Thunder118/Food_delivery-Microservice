package food_delivery.restaurant.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "restaurant")
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @Enumerated(EnumType.STRING)
    private RestaurantStatus status;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("restaurant")
    private List<MenuItem> menuItems;
}
