package pl.mlcr.protector.warehouse.inbound;

import pl.mlcr.protector.warehouse.sensor.SensorMessage;
import reactor.core.publisher.Sinks;

public interface InboundAdapter {

    void start(Sinks.Many<SensorMessage> messagesSink);
}
