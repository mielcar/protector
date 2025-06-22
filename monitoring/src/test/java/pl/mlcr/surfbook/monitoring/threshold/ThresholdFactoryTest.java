package pl.mlcr.surfbook.monitoring.threshold;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
class ThresholdFactoryTest {

    @Autowired
    ThresholdFactory thresholdFactory;

    @Test
    void shouldCreateAllThresholds() {
        assertThat(thresholdFactory.getThreshold("temperature").isPresent(), is(true));

        assertThat(thresholdFactory.getThreshold("humidity").isPresent(), is(true));
    }
}