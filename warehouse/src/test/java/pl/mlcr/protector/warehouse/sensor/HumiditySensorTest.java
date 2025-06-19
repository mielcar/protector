package pl.mlcr.protector.warehouse.sensor;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

class HumiditySensorTest {

    @Test
    void shouldProvidePortSetInProperties() {
        SensorsProperties sensorsProperties = createProperties();

        HumiditySensor humiditySensor = new HumiditySensor(sensorsProperties);

        assertThat(humiditySensor.getPort(), is(6969));
    }

    @Test
    void shouldParseMessageWithProperType() {
        SensorsProperties sensorsProperties = createProperties();

        HumiditySensor humiditySensor = new HumiditySensor(sensorsProperties);
        SensorMessage result = humiditySensor.parseMessage("sensor_id=h1; value=40");

        assertThat(result.sensorId(), is("h1"));
        assertThat(result.measurements(), hasSize(1));
        assertThat(result.measurements().getFirst().name(), is("humidity"));
        assertThat(result.measurements().getFirst().value(), is("40"));
    }

    private SensorsProperties createProperties() {
        SensorsProperties sensorsProperties = new SensorsProperties();
        SensorsProperties.SensorProperties humiditySensorProperties = new SensorsProperties.SensorProperties();
        humiditySensorProperties.setPort(6969);
        humiditySensorProperties.setType("humidity");
        sensorsProperties.setHumidity(humiditySensorProperties);
        return sensorsProperties;
    }
}