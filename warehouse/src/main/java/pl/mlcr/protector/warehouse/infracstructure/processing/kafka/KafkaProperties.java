package pl.mlcr.protector.warehouse.infracstructure.processing.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("kafka")
public class KafkaProperties {
    private String bootstrapServers;
    private String sensorTopic;
}
