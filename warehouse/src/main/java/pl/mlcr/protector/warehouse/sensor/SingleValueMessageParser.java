package pl.mlcr.protector.warehouse.sensor;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@UtilityClass
class SingleValueMessageParser {
    private static final String SPLIT_CHAR = ";";
    private static final String ID_PARAM_PREFIX = "sensor_id=";
    private static final String VALUE_PARAM_PREFIX = "value=";

    SensorMessage parse(String text, String propertyName) {
        List<String> messageParts = validateMessageParts(text);
        String sensorId = messageParts.getFirst().substring(ID_PARAM_PREFIX.length());
        String measurementValue = messageParts.getLast().substring(VALUE_PARAM_PREFIX.length());
        Measurement measurement = new Measurement(propertyName, measurementValue);
        return new SensorMessage(sensorId, Collections.singletonList(measurement));
    }

    private List<String> validateMessageParts(String message) {
        List<String> messageParts = Arrays.stream(message.split(SPLIT_CHAR))
                .filter(Objects::nonNull)
                .map(String::trim)
                .toList();
        if (messageParts.size() != 2
                || !messageParts.getFirst().startsWith(ID_PARAM_PREFIX)
                || !messageParts.getLast().startsWith(VALUE_PARAM_PREFIX))
            throw SensorMessageParseException.from(message);
        return messageParts;
    }
}
