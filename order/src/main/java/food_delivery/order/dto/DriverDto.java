package food_delivery.order.dto;


import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class DriverDto {

    @JsonAlias({"id", "Id", "ID"})
    private Long id;

    private String name;
    private String phoneNumber;
    private String status;
}
