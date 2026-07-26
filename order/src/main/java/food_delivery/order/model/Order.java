package food_delivery.order.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;


@Table(name = "orders")
@Data
@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;

    private Long restaurantId;

    private BigDecimal totalAmount;

    private String status;

    private Long driverId;
}
