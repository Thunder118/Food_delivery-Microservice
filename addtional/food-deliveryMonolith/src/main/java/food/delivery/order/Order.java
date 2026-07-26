package food.delivery.order;

import jakarta.persistence.*;
import lombok.Data;
import org.yaml.snakeyaml.tokens.ScalarToken;

import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@Data
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
