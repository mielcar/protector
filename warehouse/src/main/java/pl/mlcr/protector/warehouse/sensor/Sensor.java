package pl.mlcr.protector.warehouse.sensor;

import reactor.netty.udp.UdpInbound;

public interface Sensor {
    int getPort();

    SensorMessage parseMessage(String udpMessage);
}
