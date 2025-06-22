package pl.mlcr.surfbook.monitoring.infrastructure.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("monitoring.kafka")
public class KafkaConfigurationProperties {
    private String topic;
    private String bootstrapServers;
}
