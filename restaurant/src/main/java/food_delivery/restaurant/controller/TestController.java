package food_delivery.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/test-trace")
    public String testTrace() {

        log.info("Memeriksa apakah Trace ID sudah muncul di sini...");
        return "Trace Test OK";
    }

}
