package pl.mlcr.protector.warehouse.inbound;

import pl.mlcr.protector.warehouse.sensor.SensorMessage;
import reactor.core.publisher.Sinks;

public interface InboundAdapter {

    void bind(Sinks.Many<SensorMessage> messagesSink);
}
