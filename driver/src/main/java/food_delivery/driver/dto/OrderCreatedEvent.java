package food_delivery.driver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {

    private Long orderId;
    private Long customerId;
    private Long restaurantId;
    private Long driverId;
    private BigDecimal totalAmount; // Diubah ke BigDecimal untuk presisi nilai uang
    private String status;
}
