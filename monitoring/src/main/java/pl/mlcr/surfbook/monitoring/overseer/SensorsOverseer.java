package pl.mlcr.surfbook.monitoring.overseer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.mlcr.surfbook.monitoring.alarm.Alarm;
import pl.mlcr.surfbook.monitoring.alarm.AlarmService;
import pl.mlcr.surfbook.monitoring.input.Measurement;
import pl.mlcr.surfbook.monitoring.input.SensorMessage;
import pl.mlcr.surfbook.monitoring.input.SensorMessageInputPort;
import pl.mlcr.surfbook.monitoring.threshold.MeasurementThreshold;
import pl.mlcr.surfbook.monitoring.threshold.ThresholdFactory;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
@RequiredArgsConstructor
public class SensorsOverseer implements ApplicationListener<ApplicationReadyEvent> {
    private final SensorMessageInputPort sensorMessageInputPort;
    private final ThresholdFactory thresholdFactory;
    private final AlarmService alarmService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        sensorMessageInputPort.consume()
                .flatMap(this::checkThreshold)
                .doOnNext(alarmService::sound)
                .subscribe();
    }

    private Flux<Alarm> checkThreshold(SensorMessage sensorMessage) {
        Map<String, List<Measurement>> typeMeasurementsMap = sensorMessage.measurements().stream()
                .collect(Collectors.groupingBy(Measurement::name));
        return Flux.fromStream(typeMeasurementsMap.entrySet().stream()
                .flatMap(typeMeasurements -> checkThresholds(sensorMessage, typeMeasurements)));
    }

    private Stream<Alarm> checkThresholds(SensorMessage sensorMessage, Map.Entry<String, List<Measurement>> typeMeasurements) {
        return thresholdFactory.getThreshold(typeMeasurements.getKey()).stream()
                .flatMap(threshold -> typeMeasurements.getValue().stream()
                        .map(measurement -> createAlarm(threshold, sensorMessage, measurement))
                        .flatMap(Optional::stream));
    }

    private Optional<Alarm> createAlarm(MeasurementThreshold threshold, SensorMessage sensorMessage, Measurement measurement) {
        return threshold.isInNorm(measurement) ? Optional.empty()
                : Optional.of(Alarm.builder()
                .warehouseId(sensorMessage.warehouseId())
                .sensorId(sensorMessage.sensorId())
                .measurementType(measurement.name())
                .measurementValue(measurement.value())
                .threshold(threshold.getThreshold())
                .build());
    }
}
