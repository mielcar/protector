package pl.mlcr.protector.warehouse.sensor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SensorFactory {
    private final List<Sensor> sensors;

    public List<Sensor> getAllSensors() {
        return sensors;
    }
}
