package pl.mlcr.protector.warehouse.sensor;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

class TemperatureSensorTest {
    @Test
    void shouldProvidePortSetInProperties() {
        SensorsProperties sensorsProperties = createProperties();

        TemperatureSensor temperatureSensor = new TemperatureSensor(sensorsProperties);

        assertThat(temperatureSensor.getPort(), is(6969));
    }

    @Test
    void shouldParseMessageWithProperType() {
        SensorsProperties sensorsProperties = createProperties();

        TemperatureSensor temperatureSensor = new TemperatureSensor(sensorsProperties);
        SensorMessage result = temperatureSensor.parseMessage("sensor_id=t1; value=30");

        assertThat(result.sensorId(), is("t1"));
        assertThat(result.measurements(), hasSize(1));
        assertThat(result.measurements().getFirst().name(), is("temperature"));
        assertThat(result.measurements().getFirst().value(), is("30"));
    }

    private SensorsProperties createProperties() {
        SensorsProperties sensorsProperties = new SensorsProperties();
        SensorsProperties.SensorProperties temperatureSensorProperties = new SensorsProperties.SensorProperties();
        temperatureSensorProperties.setPort(6969);
        temperatureSensorProperties.setType("temperature");
        sensorsProperties.setTemperature(temperatureSensorProperties);
        return sensorsProperties;
    }
}