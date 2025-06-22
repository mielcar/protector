package pl.mlcr.protector.warehouse.infracstructure.processing.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.stereotype.Component;
import pl.mlcr.protector.warehouse.processing.OutboundPublisher;
import pl.mlcr.protector.warehouse.sensor.SensorMessage;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaPublisher implements OutboundPublisher {
    private final ReactiveKafkaProducerTemplate<String, Object> kafkaTemplate;
    private final NewTopic sensorTopic;

    @Value("${warehouse.id}")
    private String warehouseId;

    @Override
    public Mono<SenderResult<Void>> publish(SensorMessage message) {
        return kafkaTemplate.send(sensorTopic.name(), warehouseId, message)
                .doOnSuccess(result -> log.info("Message sent successfully: {}", result.recordMetadata()))
                .doOnError(error -> log.error("Failed to send message: {}", error.getMessage()));
    }
}
