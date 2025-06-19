package pl.mlcr.protector.warehouse.inbound;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.mlcr.protector.warehouse.sensor.Sensor;
import pl.mlcr.protector.warehouse.sensor.SensorMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.netty.Connection;
import reactor.netty.udp.UdpServer;

@Slf4j
@RequiredArgsConstructor
public class UdpInboundAdapter {
    private final Connection server;
    private final Flux<SensorMessage> messagesFlux;

    static UdpInboundAdapter from(Sensor sensor) {
        Sinks.Many<SensorMessage> messagesSink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<SensorMessage> sensorMessageFlux = messagesSink.asFlux()
                .doOnSubscribe(s -> log.debug("New subscription to UDP messages stream"))
                .doOnCancel(() -> log.debug("Subscription to UDP messages stream cancelled"))
                .share();
        UdpSensorMessageHandler handler = new UdpSensorMessageHandler(sensor, messagesSink);
        Connection server = UdpServer.create()
                .port(sensor.getPort())
                .handle(handler::handleUdpMessage)
                .bindNow();
        return new UdpInboundAdapter(server, sensorMessageFlux);
    }
}
