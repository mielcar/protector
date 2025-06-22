package pl.mlcr.surfbook.monitoring.alarm;

import lombok.Builder;

@Builder
public record Alarm(String warehouseId, String sensorId, String measurementType, String measurementValue, String threshold) {
}
