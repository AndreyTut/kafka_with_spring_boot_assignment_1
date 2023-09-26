package my.kafka.study.trackingservice.service;

import lombok.RequiredArgsConstructor;
import my.kafka.study.trackingservice.dto.StatusUpdatedDto;
import my.kafka.study.trackingservice.entity.StatusUpdatedEntity;
import my.kafka.study.trackingservice.model.TrackingStatusUpdated;
import my.kafka.study.trackingservice.repository.StatusUpdatedEntityRepository;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackingStatusUpdatedStatisticService {

    private final StatusUpdatedEntityRepository repository;

    public void create(TrackingStatusUpdated statusUpdated, SendResult<String, Object> sendResult) {
        repository.save(
                StatusUpdatedEntity.builder()
                        .orderId(statusUpdated.getOrderId().toString())
                        .status(statusUpdated.getStatus().toString())
                        .createdAt(LocalDateTime.ofEpochSecond(sendResult.getRecordMetadata().timestamp() / 1000, 0, ZoneOffset.UTC))
                        .build()
        );
    }

    public StatusUpdatedDto findByOrderId(String orderId) {
        return new StatusUpdatedDto(repository.findByOrderId(orderId));
    }

    public List<StatusUpdatedDto> findAll() {
        return repository.findAll().stream().map(StatusUpdatedDto::new).toList();
    }
}
