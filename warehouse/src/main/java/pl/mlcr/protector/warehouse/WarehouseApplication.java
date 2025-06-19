package pl.mlcr.protector.warehouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import pl.mlcr.protector.warehouse.sensor.SensorsProperties;

@SpringBootApplication
@EnableConfigurationProperties({SensorsProperties.class})
public class WarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

}
