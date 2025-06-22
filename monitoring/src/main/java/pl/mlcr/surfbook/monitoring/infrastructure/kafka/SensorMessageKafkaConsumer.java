package pl.mlcr.surfbook.monitoring.infrastructure.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.reactive.ReactiveKafkaConsumerTemplate;
import org.springframework.stereotype.Component;
import pl.mlcr.surfbook.monitoring.input.SensorMessage;
import pl.mlcr.surfbook.monitoring.input.SensorMessageInputPort;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
class SensorMessageKafkaConsumer implements SensorMessageInputPort {
    private final ReactiveKafkaConsumerTemplate<String, SensorMessage> kafkaConsumer;

    @Override
    public Flux<SensorMessage> consume() {
        return kafkaConsumer
                .receiveAutoAck()
                .doOnNext(consumerRecord -> log.info("received key={}, value={} from topic={}, offset={}",
                        consumerRecord.key(),
                        consumerRecord.value(),
                        consumerRecord.topic(),
                        consumerRecord.offset())
                )
                .map(ConsumerRecord::value)
                .doOnNext(message -> log.info("successfully consumed {}={}", SensorMessage.class.getSimpleName(), message))
                .doOnError(throwable -> log.error("something bad happened while consuming : {}", throwable.getMessage()));
    }
}
