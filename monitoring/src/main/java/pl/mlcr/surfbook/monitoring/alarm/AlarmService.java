package pl.mlcr.surfbook.monitoring.alarm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AlarmService {
    public void sound(Alarm alarm) {
        log.error("Error, measurement exceeds configured threshold! warehouse ID: {}, measurement type: {}, value: {}, threshold: {}",
                alarm.warehouseId(), alarm.measurementType(), alarm.measurementValue(), alarm.threshold());
    }
}
