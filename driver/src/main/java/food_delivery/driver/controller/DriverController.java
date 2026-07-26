package food_delivery.driver.controller;

import food_delivery.driver.model.Driver;
import food_delivery.driver.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService driverService;

    @PostMapping
    public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) {
        Driver newDriver = driverService.createDriver(driver);
        return ResponseEntity.status(HttpStatus.CREATED).body(newDriver);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable Long id) {
        Driver driver = driverService.getDriverById(id);
        return ResponseEntity.ok(driver);
    }
}
