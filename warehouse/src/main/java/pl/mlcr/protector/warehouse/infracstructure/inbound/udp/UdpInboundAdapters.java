package pl.mlcr.protector.warehouse.infracstructure.inbound.udp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mlcr.protector.warehouse.inbound.InboundAdapter;
import pl.mlcr.protector.warehouse.sensor.SensorFactory;
import pl.mlcr.protector.warehouse.sensor.SensorMessage;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
class UdpInboundAdapters implements InboundAdapter {
    private final SensorFactory sensorFactory;
    private final List<UdpInboundAdapter> adapters = new ArrayList<>();

    @Override
    public void start(Sinks.Many<SensorMessage> messagesSink) {
        adapters.addAll(sensorFactory.getAllSensors().stream()
                .map(sensor -> UdpInboundAdapter.from(sensor, messagesSink))
                .toList());
    }
}
