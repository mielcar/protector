package pl.mlcr.surfbook.monitoring.threshold;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.mlcr.surfbook.monitoring.input.Measurement;

@Slf4j
@Component
class HumidityThreshold implements MeasurementThreshold {
    private static final String TYPE = "humidity";
    private final double threshold;

    public HumidityThreshold(ThresholdProperties thresholdProperties) {
        this.threshold = thresholdProperties.getHumidity();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public boolean isInNorm(Measurement measurement) {
        try {
            double humidity = Double.parseDouble(measurement.value());
            return humidity <= threshold;
        } catch (NumberFormatException e) {
            log.error("Wrong humidity value: {}", measurement.value());
            return false;
        }
    }

    @Override
    public String getThreshold() {
        return String.valueOf(threshold);
    }
}
