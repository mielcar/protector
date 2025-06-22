package pl.mlcr.surfbook.monitoring.input;

import lombok.With;

import java.util.List;

public record SensorMessage(@With String warehouseId, String sensorId, List<Measurement> measurements) {
}
