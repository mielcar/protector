package pl.mlcr.protector.warehouse.sensor;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
class TemperatureSensor implements Sensor {
    private final int port = 3344;
    @Override
    public SensorMessage parseMessage(String udpMessage) {
        return null;
    }
}
