package pl.mlcr.protector.warehouse.processing;

import pl.mlcr.protector.warehouse.sensor.SensorMessage;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.SenderResult;

public interface OutboundPublisher {
    Mono<SenderResult<Void>> publish(SensorMessage message);
}
