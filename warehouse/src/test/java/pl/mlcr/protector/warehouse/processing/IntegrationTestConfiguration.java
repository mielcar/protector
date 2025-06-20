package pl.mlcr.protector.warehouse.processing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
class IntegrationTestConfiguration {

    @Bean
    @Primary
    public SensorMessagesProcessor sensorMessagesProcessor() {
        return mock(SensorMessagesProcessor.class);
    }
}
