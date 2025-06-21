package pl.mlcr.protector.warehouse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import pl.mlcr.protector.warehouse.infracstructure.processing.kafka.KafkaTopicConfig;
import pl.mlcr.protector.warehouse.processing.SensorMessagesProcessor;

import static org.mockito.Mockito.mock;

@Configuration
class IntegrationTestConfiguration {

    @Bean
    @Primary
    public SensorMessagesProcessor sensorMessagesProcessor() {
        return mock(SensorMessagesProcessor.class);
    }

    @Bean
    @Primary
    public KafkaTopicConfig kafkaTopicConfig() {
        return mock(KafkaTopicConfig.class);
    }
}
