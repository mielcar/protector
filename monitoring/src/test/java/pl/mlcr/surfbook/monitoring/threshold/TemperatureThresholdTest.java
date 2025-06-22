package pl.mlcr.surfbook.monitoring.threshold;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.mlcr.surfbook.monitoring.input.Measurement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class TemperatureThresholdTest {

    TemperatureThreshold threshold;

    public TemperatureThresholdTest() {
        ThresholdProperties thresholdProperties = new ThresholdProperties();
        thresholdProperties.setTemperature(35);
        threshold = new TemperatureThreshold(thresholdProperties);
    }

    @ParameterizedTest
    @CsvSource({"34.9,true", "35,true", "35.1,false"})
    void shouldProperlyCalculateThreshold(String temperature, boolean expected) {
        Measurement measurement = new Measurement("temperature", temperature);
        assertThat(threshold.isInNorm(measurement), is(expected));
    }

    @Test
    void shouldReturnNotInNormForWrongFormatTemperature() {
        Measurement measurement = new Measurement("temperature", "xxx");
        assertThat(threshold.isInNorm(measurement), is(false));
    }
}