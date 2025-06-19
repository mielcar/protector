package pl.mlcr.protector.warehouse.inbound;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mlcr.protector.warehouse.sensor.SensorFactory;
import reactor.netty.udp.UdpServer;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InboundAdapters {
    private final SensorFactory sensorFactory;
    private final List<UdpServer> udpServers = new ArrayList<>();


}
