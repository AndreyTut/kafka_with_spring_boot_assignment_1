package my.kafka.study.trackingservice.dto;

import lombok.Data;
import my.kafka.study.trackingservice.entity.StatusUpdatedEntity;

import java.time.LocalDateTime;

@Data

public class StatusUpdatedDto {
    private String orderId;
    private String status;
    private LocalDateTime createdAt;

    public StatusUpdatedDto(StatusUpdatedEntity entity) {
        this.orderId = entity.getOrderId();
        this.status = entity.getStatus();
        this.createdAt = entity.getCreatedAt();
    }
}
