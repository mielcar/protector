package pl.mlcr.protector.warehouse.inbound;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import pl.mlcr.protector.warehouse.sensor.Sensor;
import pl.mlcr.protector.warehouse.sensor.SensorMessage;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.netty.udp.UdpInbound;
import reactor.netty.udp.UdpOutbound;

@Slf4j
@RequiredArgsConstructor
class UdpSensorMessageHandler {
    private final Sensor sensor;
    private final Sinks.Many<SensorMessage> messagesSink;

    Publisher<Void> handleUdpMessage(UdpInbound inbound, UdpOutbound outbound) {
        return inbound.receive()
                .asString()
                .doOnNext(message -> log.debug("Received UDP message: '{}'", message))
                .map(sensor::parseMessage)
                .doOnNext(sensorMessage -> {
                    Sinks.EmitResult result = messagesSink.tryEmitNext(sensorMessage);
                    if (result.isFailure()) {
                        log.error("Failed to emit sensor message: {}", result);
                    }
                })
                .doOnError(error -> log.error("Error processing UDP message", error))
                .onErrorResume(error -> {
                    log.warn("Resuming after error: {}", error.getMessage());
                    return Mono.empty();
                })
                .then();
    }
}
