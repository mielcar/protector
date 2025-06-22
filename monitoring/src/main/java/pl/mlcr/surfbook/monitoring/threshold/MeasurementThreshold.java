package pl.mlcr.surfbook.monitoring.threshold;

import pl.mlcr.surfbook.monitoring.input.Measurement;

public interface MeasurementThreshold {
    String getType();

    boolean isInNorm(Measurement measurement);
}
