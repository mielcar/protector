package pl.mlcr.protector.warehouse.sensor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
class SensorFactoryTest {

    @Autowired
    SensorFactory sensorFactory;

    @Test
    void allSensorTypesAreCreated() {
        List<Sensor> result = sensorFactory.getAllSensors();

        assertThat(result, hasSize(2));
    }
}