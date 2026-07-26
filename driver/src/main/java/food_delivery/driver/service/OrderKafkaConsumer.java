package food_delivery.driver.service;

import food_delivery.driver.dto.OrderCreatedEvent;
import food_delivery.driver.repository.DriverRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderKafkaConsumer {

    private final DriverRepository driverRepository;

    @KafkaListener(topics = "order-created-topic", groupId = "driver-group")
    @Transactional
    @CacheEvict(value = "driver", key = "#event.driverId")
    public void consumeOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("📩 [Kafka Consumer] Menerima event order baru: Order ID {} untuk Driver ID {}",
                event.getOrderId(), event.getDriverId());

        if (event.getDriverId() != null) {
            driverRepository.findById(event.getDriverId()).ifPresentOrElse(driver -> {
                driver.setStatus("BUSY");
                driverRepository.save(driver);
                log.info("✅ Status Driver ID {} berhasil diubah menjadi 'BUSY'", driver.getId());
            }, () -> log.warn("⚠️ Driver dengan ID {} tidak ditemukan", event.getDriverId()));
        }
    }
}