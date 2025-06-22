package pl.mlcr.surfbook.monitoring.threshold;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pl.mlcr.surfbook.monitoring.input.Measurement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class HumidityThresholdTest {

    HumidityThreshold threshold;

    public HumidityThresholdTest() {
        ThresholdProperties thresholdProperties = new ThresholdProperties();
        thresholdProperties.setHumidity(50);
        threshold = new HumidityThreshold(thresholdProperties);
    }

    @ParameterizedTest
    @CsvSource({"49.9,true", "50,true", "50.1,false"})
    void shouldProperlyCalculateThreshold(String humidity, boolean expected) {
        Measurement measurement = new Measurement("humidity", humidity);
        assertThat(threshold.isInNorm(measurement), is(expected));
    }

    @Test
    void shouldReturnNotInNormForWrongFormatTemperature() {
        Measurement measurement = new Measurement("humidity", "xxx");
        assertThat(threshold.isInNorm(measurement), is(false));
    }
}