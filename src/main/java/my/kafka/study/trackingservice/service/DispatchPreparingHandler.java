package my.kafka.study.trackingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.kafka.study.trackingservice.model.DispatchPreparing;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DispatchPreparingHandler {

    private final TrackingStatusService trackingStatusService;

    @KafkaListener(
            id = "dispatchPreparingConsumerClient",
            topics = "${kafka.tracking.topic}",
            groupId = "tracking.dispatch.preparing.consumer",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(DispatchPreparing payload) {
        log.info("Received message: payload: " + payload);
        try {
            trackingStatusService.process(payload);
        } catch (Exception e) {
            log.error("Processing failure", e);
        }
    }
}
