package my.kafka.study.trackingservice.controller;

import lombok.RequiredArgsConstructor;
import my.kafka.study.trackingservice.dto.StatusUpdatedDto;
import my.kafka.study.trackingservice.service.TrackingStatusUpdatedStatisticService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/statistic")
public class StatisticController {
    private final TrackingStatusUpdatedStatisticService service;

    @GetMapping("/status-updated")
    public List<StatusUpdatedDto> statusUpdated() {
        return service.findAll();
    }

    @GetMapping("/status-updated/{id}")
    public Object statusUpdatedLast(@PathVariable String id) {
        return service.findByOrderId(id);
    }
}
