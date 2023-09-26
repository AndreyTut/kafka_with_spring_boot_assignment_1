package my.kafka.study.trackingservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import my.kafka.study.trackingservice.model.DispatchPreparing;
import my.kafka.study.trackingservice.model.Status;
import my.kafka.study.trackingservice.model.TrackingStatusUpdated;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrackingStatusService {

    @Value("${kafka.tracking-status.topic}")
    private String statusTrackingTopic;

    private final KafkaTemplate<String, Object> producer;
    private final TrackingStatusUpdatedStatisticService statisticService;

    public void process(DispatchPreparing preparing) throws ExecutionException, InterruptedException {
        TrackingStatusUpdated statusUpdated = TrackingStatusUpdated.builder()
                .orderId(preparing.getOrderId())
                .status(Status.PREPARING)
                .build();
        SendResult<String, Object> sendResult = producer.send(statusTrackingTopic, statusUpdated).get();
        statisticService.create(statusUpdated, sendResult);
        log.info("Sent message:" + sendResult.getProducerRecord().value());
    }
}
