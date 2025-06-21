package pl.mlcr.protector.warehouse.processing;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import pl.mlcr.protector.warehouse.inbound.InboundAdapters;
import pl.mlcr.protector.warehouse.sensor.SensorMessage;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class SensorMessagesProcessor implements ApplicationListener<ApplicationReadyEvent> {
    private final InboundAdapters inboundAdapters;
    private final OutboundPublisher outboundPublisher;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Flux<SensorMessage> messageFlux = inboundAdapters.start();
        messageFlux.flatMap(outboundPublisher::publish)
                .subscribe();
    }
}
