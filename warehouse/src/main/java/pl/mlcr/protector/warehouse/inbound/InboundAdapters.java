package pl.mlcr.protector.warehouse.inbound;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.mlcr.protector.warehouse.sensor.SensorMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InboundAdapters {
    private final List<InboundAdapter> inboundAdapters;

    public Flux<SensorMessage> start() {
        Sinks.Many<SensorMessage> messagesSink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<SensorMessage> sensorMessageFlux = messagesSink.asFlux()
                .doOnSubscribe(s -> log.debug("New subscription to UDP messages stream"))
                .doOnCancel(() -> log.debug("Subscription to UDP messages stream cancelled"))
                .share();
        inboundAdapters.forEach(inboundAdapter -> inboundAdapter.bind(messagesSink));
        return sensorMessageFlux;
    }
}
