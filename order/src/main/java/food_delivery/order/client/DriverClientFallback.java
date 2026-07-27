package food_delivery.order.client;

import food_delivery.order.dto.DriverDto;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DriverClientFallback implements DriverClient {
    @Override
    public DriverDto getDriverById(Long id) {
        log.error("⚠️ [Circuit Breaker - Feign] Driver Service DOWN! Mengembalikan data fallback untuk Driver ID: {}", id);

        DriverDto fallbackDriver = new DriverDto();
        fallbackDriver.setId(id);
        fallbackDriver.setName("DRIVER_SERVICE_UNAVAILABLE");
        fallbackDriver.setStatus("UNAVAILABLE");
        return  fallbackDriver;
    }
}
