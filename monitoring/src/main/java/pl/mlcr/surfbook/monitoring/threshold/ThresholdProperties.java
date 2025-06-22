package pl.mlcr.surfbook.monitoring.threshold;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("monitoring.threshold")
public class ThresholdProperties {
    private double temperature;
    private double humidity;
}
