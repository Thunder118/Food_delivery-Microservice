package food_delivery.order.client;

import food_delivery.order.dto.DriverDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "driver-service", url = "${driver.service.url:http://localhost:8082}", fallback = DriverClientFallback.class)
public interface DriverClient {

    @GetMapping("/public/drivers/{id}")
    DriverDto getDriverById(@PathVariable("id") Long id);
}
