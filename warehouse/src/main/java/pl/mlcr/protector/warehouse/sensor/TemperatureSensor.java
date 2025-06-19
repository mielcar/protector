package pl.mlcr.protector.warehouse.sensor;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
class TemperatureSensor extends SingleValueSensor {
    public TemperatureSensor(SensorsProperties sensorsProperties) {
        super(sensorsProperties.getTemperature());
    }
}
