package pl.mlcr.surfbook.monitoring.threshold;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pl.mlcr.surfbook.monitoring.input.Measurement;

@Slf4j
@Component
class TemperatureThreshold implements MeasurementThreshold {
    private static final String TYPE = "temperature";

    private final double threshold;

    public TemperatureThreshold(ThresholdProperties properties) {
        this.threshold = properties.getTemperature();
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public boolean isInNorm(Measurement measurement) {
        try {
            double temperature = Double.parseDouble(measurement.value());
            return temperature <= threshold;
        } catch (NumberFormatException e) {
            log.error("Wrong temperature value: {}", measurement.value());
            return false;
        }
    }
}
