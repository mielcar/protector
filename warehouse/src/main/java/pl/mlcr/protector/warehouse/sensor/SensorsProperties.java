package pl.mlcr.protector.warehouse.sensor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("warehouse.sensor")
public class SensorsProperties {
    private SensorProperties temperature;
    private SensorProperties humidity;

    @Getter
    @Setter
    public static class SensorProperties {
        private int port;
        private String type;
    }
}
