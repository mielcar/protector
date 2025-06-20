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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Slf4j
@SpringBootTest
class UdpInboundAdaptersIntegrationTest {

    @Autowired
    UdpInboundAdapters udpInboundAdapters;

    @Test
    void shouldStartAdapters() throws Exception {
        Sinks.Many<SensorMessage> messagesSink = Sinks.many().multicast().onBackpressureBuffer();
        udpInboundAdapters.start(messagesSink);
        Flux<SensorMessage> sensorMessageFlux = messagesSink.asFlux();
        CountDownLatch latch = new CountDownLatch(2);

        sensorMessageFlux
                .doOnNext(sensorMessage -> latch.countDown())
                .subscribe();

        sendMessage("sensor_id=t1; value=30", 3344);
        sendMessage("sensor_id=h1; value=50", 3355);

        boolean result = latch.await(1, TimeUnit.SECONDS);
        assertThat(result, is(true));
    }

    @Test
    void playground() throws Exception {
        sendMessage("sensor_id=t1; value=30", 3355);
    }

    private void sendMessage(String message, int port) throws Exception {
        DatagramSocket client = new DatagramSocket();

        byte[] messageBytes = message.getBytes();
        DatagramPacket packet = new DatagramPacket(
                messageBytes, messageBytes.length,
                InetAddress.getByName("127.0.0.1"), port
        );

        client.send(packet);
    }
}