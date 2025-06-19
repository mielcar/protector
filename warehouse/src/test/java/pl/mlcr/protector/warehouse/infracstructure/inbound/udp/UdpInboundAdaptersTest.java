package pl.mlcr.protector.warehouse.infracstructure.inbound.udp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.mlcr.protector.warehouse.sensor.SensorMessage;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Slf4j
@SpringBootTest
class UdpInboundAdaptersTest {

    @Autowired
    UdpInboundAdapters udpInboundAdapters;

    @Test
    void shouldStartAdapters() throws Exception {
        Sinks.Many<SensorMessage> messagesSink = Sinks.many().multicast().onBackpressureBuffer();
        Flux<SensorMessage> sensorMessageFlux = messagesSink.asFlux()
                .doOnSubscribe(s -> log.error("New subscription to UDP messages stream"))
                .doOnCancel(() -> log.error("Subscription to UDP messages stream cancelled"))
                .share();

        udpInboundAdapters.bind(messagesSink);
        sensorMessageFlux
                .doOnNext(sensorMessage -> log.error(sensorMessage.sensorId()))
                .subscribe();
        sendMessage("sensor_id=t1; value=30");
        Thread.sleep(10000);
        sendMessage("sensor_id=t1; value=30");
    }

    private void sendMessage(String message) throws Exception {
        DatagramSocket client = new DatagramSocket();

        byte[] messageBytes = message.getBytes();
        DatagramPacket packet = new DatagramPacket(
                messageBytes, messageBytes.length,
                InetAddress.getLocalHost(), 3344
        );

        client.send(packet);
    }
}