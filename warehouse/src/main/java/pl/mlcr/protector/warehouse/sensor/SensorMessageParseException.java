package pl.mlcr.protector.warehouse.sensor;


public class SensorMessageParseException extends IllegalArgumentException {
    private static final String MESSAGE_TEMPLATE = "Error parsing sensor message: '%s'";

    private SensorMessageParseException(String message) {
        super(new IllegalArgumentException(message));
    }

    static SensorMessageParseException from(String message) {
        String exceptionMessage = String.format(MESSAGE_TEMPLATE, message);
        return new SensorMessageParseException(exceptionMessage);
    }
}
