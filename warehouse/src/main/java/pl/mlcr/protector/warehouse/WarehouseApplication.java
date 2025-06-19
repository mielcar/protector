package pl.mlcr.protector.warehouse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.mlcr.protector.warehouse.sensor.SensorsProperties;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
@EnableConfigurationProperties({SensorsProperties.class})
public class WarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }
}
