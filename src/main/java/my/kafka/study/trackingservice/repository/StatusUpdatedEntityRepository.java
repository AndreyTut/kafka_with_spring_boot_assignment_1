package my.kafka.study.trackingservice.repository;

import my.kafka.study.trackingservice.entity.StatusUpdatedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusUpdatedEntityRepository extends JpaRepository<StatusUpdatedEntity, Long> {
    StatusUpdatedEntity findByOrderId(String orderId);
}
