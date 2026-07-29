package food_delivery.order.client;

import food_delivery.order.dto.DriverDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "DRIVER-SERVICE",url = "${services.driver.url}", fallback = DriverClientFallback.class)
public interface DriverClient {

    @GetMapping("/{id}")
    DriverDto getDriverById(@PathVariable("id") Long id);
}
