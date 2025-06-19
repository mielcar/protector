package pl.mlcr.protector.warehouse.processing;

import pl.mlcr.protector.warehouse.sensor.SensorMessage;

public interface OutboundPublisher {
    void publish(SensorMessage message);
}
