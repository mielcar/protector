package pl.mlcr.protector.warehouse.sensor;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SingleValueMessageParserTest {

    @Test
    void shouldParseProperMessage() {
        String textMessage = "sensor_id=h1; value=40";

        SensorMessage result = SingleValueMessageParser.parse(textMessage, "humidity");

        assertThat(result.sensorId(), is("h1"));
        assertThat(result.measurements(), hasSize(1));
        Measurement resultMeasurement = result.measurements().getFirst();
        assertThat(resultMeasurement.name(), is("humidity"));
        assertThat(resultMeasurement.value(), is("40"));
    }

    @Test
    void shouldThrowSensorMessageParseException() {
        String textMessage = "xxx";

        SensorMessageParseException result = assertThrows(SensorMessageParseException.class, () ->
                SingleValueMessageParser.parse(textMessage, "humidity"));

        assertThat(result.getMessage(), is("java.lang.IllegalArgumentException: Error parsing sensor message: 'xxx'"));
    }
}