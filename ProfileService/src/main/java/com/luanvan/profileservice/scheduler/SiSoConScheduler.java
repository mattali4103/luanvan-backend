package com.luanvan.profileservice.scheduler;

import com.luanvan.profileservice.services.LopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SiSoConScheduler {

    private final LopService lopService;

    /**
     * Cập nhật siSoCon cho tất cả các lớp mỗi ngày lúc 2:00 AM
     */
    @Scheduled(cron = "0 0 2 * * *")
    public void updateAllSiSoCon() {
        log.info("Bắt đầu cập nhật siSoCon cho tất cả các lớp");
        try {
            lopService.updateAllSiSoCon();
            log.info("Cập nhật siSoCon thành công");
        } catch (Exception e) {
            log.error("Lỗi khi cập nhật siSoCon: {}", e.getMessage(), e);
        }
    }
}
