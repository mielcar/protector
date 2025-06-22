package pl.mlcr.surfbook.monitoring.overseer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.mlcr.surfbook.monitoring.input.SensorMessageInputPort;

import java.util.function.Function;

@Slf4j
@Component
@RequiredArgsConstructor
public class SensorsOverseer implements ApplicationListener<ApplicationReadyEvent> {
    private final SensorMessageInputPort sensorMessageInputPort;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        sensorMessageInputPort.consume()
                .doOnNext(sensorMessage -> log.error(sensorMessage.sensorId()))
                .map(Function.identity())
                .subscribe();
    }
}
