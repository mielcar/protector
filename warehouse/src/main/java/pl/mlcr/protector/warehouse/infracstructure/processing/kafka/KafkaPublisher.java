package pl.mlcr.protector.warehouse.infracstructure.processing.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.mlcr.protector.warehouse.processing.OutboundPublisher;
import pl.mlcr.protector.warehouse.sensor.SensorMessage;

@Slf4j
@Component
public class KafkaPublisher implements OutboundPublisher {

    @Override
    public void publish(SensorMessage message) {
        log.error("Publishing message from sensor: {}, type: {}, value: {}", message.sensorId(),
                message.measurements().getFirst().name(),
                message.measurements().getFirst().value());
    }
}
