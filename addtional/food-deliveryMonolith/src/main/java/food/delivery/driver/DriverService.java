package food.delivery.driver;

import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class DriverService {

    private final DriverRepository driverRepository;

    public Driver createDriver(Driver driver){
        driver.setStatus("AVAILABLE");
        return driverRepository.save(driver);
    }

    @Transactional
    public Driver assignAvailableDriver() {
        Driver driver = driverRepository.findFirstByStatus("AVAILABLE")
                .orElse(null);

        if (driver != null) {
            driver.setStatus("BUSY");
            driverRepository.save(driver);
        }

        return driver;
    }
}
