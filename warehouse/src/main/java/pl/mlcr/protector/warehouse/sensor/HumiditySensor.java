package pl.mlcr.protector.warehouse.sensor;

import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Component
class HumiditySensor extends SingleValueSensor {
    public HumiditySensor(SensorsProperties sensorsProperties) {
        super(sensorsProperties.getHumidity());
    }
}
