package pl.mlcr.surfbook.monitoring.input;

import reactor.core.publisher.Flux;

public interface SensorMessageInputPort {
    Flux<SensorMessage> consume();
}
