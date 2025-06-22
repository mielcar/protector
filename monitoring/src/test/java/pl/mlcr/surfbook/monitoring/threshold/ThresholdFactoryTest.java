package pl.mlcr.surfbook.monitoring.threshold;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mlcr.surfbook.monitoring.input.Measurement;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class ThresholdFactoryTest {

    @Autowired
    ThresholdFactory thresholdFactory;

    @Test
    void shouldCreateAllThresholds() {
        Measurement temperature = new Measurement("temperature", "30");
        assertThat(thresholdFactory.getThreshold(temperature).isPresent(), is(true));

        Measurement humidity = new Measurement("humidity", "35");
        assertThat(thresholdFactory.getThreshold(humidity).isPresent(), is(true));
    }
}