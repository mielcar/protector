package pl.mlcr.surfbook.monitoring.threshold;

import org.springframework.stereotype.Component;
import pl.mlcr.surfbook.monitoring.input.Measurement;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ThresholdFactory {
    private final Map<String, MeasurementThreshold> thresholds;

    public ThresholdFactory(List<MeasurementThreshold> thresholds) {
        this.thresholds = thresholds.stream()
                .collect(Collectors.groupingBy(MeasurementThreshold::getType,
                        Collectors.reducing(null, (a, b) -> b)));
    }

    public Optional<MeasurementThreshold> getThreshold(Measurement measurement) {
        return Optional.ofNullable(thresholds.get(measurement.name()));
    }
}
