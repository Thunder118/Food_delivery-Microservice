package food_delivery.order.service;

import food_delivery.order.config.KafkaTopicConfig;
import food_delivery.order.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderKafkaProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public void sendOrderCreatedEvent(OrderCreatedEvent event) {
        log.info("🚀 [Kafka Producer] Mengirim event untuk Driver ID: {}", event.getDriverId());
        kafkaTemplate.send(KafkaTopicConfig.ORDER_CREATED_TOPIC, event);
    }


}
