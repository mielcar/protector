package pl.mlcr.protector.warehouse;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.mlcr.protector.warehouse.inbound.InboundAdapters;
import pl.mlcr.protector.warehouse.sensor.SensorMessage;
import pl.mlcr.protector.warehouse.sensor.SensorsProperties;
import reactor.core.publisher.Flux;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties({SensorsProperties.class})
public class WarehouseApplication {

    private final InboundAdapters inboundAdapters;

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

    @PostConstruct
    void startInboundAdapters() {
        Flux<SensorMessage> messages = inboundAdapters.start();
        messages.doOnNext(sensorMessage -> log.error(sensorMessage.sensorId()))
                .subscribe();
    }
}
