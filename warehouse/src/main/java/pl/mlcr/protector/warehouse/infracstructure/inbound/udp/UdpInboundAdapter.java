package pl.mlcr.protector.warehouse.infracstructure.inbound.udp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.mlcr.protector.warehouse.sensor.Sensor;
import pl.mlcr.protector.warehouse.sensor.SensorMessage;
import reactor.core.publisher.Sinks;
import reactor.netty.Connection;
import reactor.netty.udp.UdpServer;

@Slf4j
@RequiredArgsConstructor
class UdpInboundAdapter {
    private final Connection server;

    static UdpInboundAdapter from(Sensor sensor, Sinks.Many<SensorMessage> messagesSink, String host) {
        UdpSensorMessageHandler handler = new UdpSensorMessageHandler(sensor, messagesSink);
        Connection server = UdpServer.create()
                .port(sensor.getPort())
                .host(host)
                .handle(handler::handleUdpMessage)
                .bindNow();
        log.info("UDP server started on: {}:{}", host, sensor.getPort());
        return new UdpInboundAdapter(server);
    }
}
