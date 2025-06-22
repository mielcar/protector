package pl.mlcr.surfbook.monitoring.input;

import java.util.List;

public record SensorMessage(String warehouseId, String sensorId, List<Measurement> measurements) {
}
