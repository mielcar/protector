package pl.mlcr.protector.warehouse.sensor;

public interface Sensor {
    int getPort();

    SensorMessage parseMessage(String udpMessage);
}
