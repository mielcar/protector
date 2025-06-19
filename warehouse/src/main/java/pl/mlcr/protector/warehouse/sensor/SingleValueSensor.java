package pl.mlcr.protector.warehouse.sensor;

import lombok.Getter;

abstract class SingleValueSensor implements Sensor {
    @Getter
    private final int port;
    private final String sensorType;

    public SingleValueSensor(SensorsProperties.SensorProperties sensorProperties) {
        this.port = sensorProperties.getPort();
        this.sensorType = sensorProperties.getType();
    }

    @Override
    public SensorMessage parseMessage(String udpMessage) {
        return SingleValueMessageParser.parse(udpMessage, sensorType);
    }
}
