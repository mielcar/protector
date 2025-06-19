package pl.mlcr.protector.warehouse.sensor;

import java.util.List;

public record SensorMessage(String sensorId, List<Measurement> measurements) {
}
