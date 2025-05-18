package io.github.sunjoo_kim.board.cron;

import io.github.sunjoo_kim.board.redis.BoardStayTimeRecorder;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StayLogCleanupScheduler {

    private final BoardStayTimeRecorder boardStayTimeRecorder;

    // 1시간마다 실행 (매시 0분 0초)
    @Scheduled(cron = "0 0 0 * * *")
    public void scheduleStayLogCleanup() {
        boardStayTimeRecorder.cleanUpOldStayLogs();
    }
}
