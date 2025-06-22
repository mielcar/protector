package pl.mlcr.surfbook.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.mlcr.surfbook.monitoring.infrastructure.kafka.KafkaConfigurationProperties;
import pl.mlcr.surfbook.monitoring.threshold.ThresholdProperties;

@SpringBootApplication
@EnableConfigurationProperties({KafkaConfigurationProperties.class, ThresholdProperties.class})
public class MonitoringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitoringApplication.class, args);
    }

}
