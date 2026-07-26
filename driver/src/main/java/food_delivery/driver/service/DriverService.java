package food_delivery.driver.service;

import food_delivery.driver.model.Driver;
import food_delivery.driver.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DriverService {

    private final DriverRepository driverRepository;

    @Transactional
    public Driver createDriver(Driver driver) {
        driver.setStatus("AVAILABLE");
        return driverRepository.save(driver);
    }

    @Transactional
    @CacheEvict(value = "driver", key = "#result.id")
    public Driver assignAvailableDriver() {
        Driver driver = driverRepository.findFirstByStatus("AVAILABLE")
                .orElseThrow(() -> new IllegalStateException("Tidak ada driver yang tersedia saat ini"));

        driver.setStatus("BUSY");
        return driverRepository.save(driver);
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "driver", key = "#id")
    public Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Driver tidak ditemukan untuk ID: " + id));
    }
}
