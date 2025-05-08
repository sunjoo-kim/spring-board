package io.github.sunjoo_kim.board.cron;


import io.github.sunjoo_kim.board.redis.BoardViewRedisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
@Slf4j
public class BoardViewCountScheduler {

    private final BoardViewRedisRepository redisRepository;

    @Scheduled(cron = "0 0 * * * *") // Every hour
    public void updateHourlyViewCounts() {
        Set<String> keys = redisRepository.getKeys("board:*:viewsLast1Hour");
        for (String key : keys) {
            redisRepository.setViewCount(key, 0L); // Reset hourly views
        }
        log.info("Hourly view counts updated.");
    }

    @Scheduled(cron = "0 0 0 * * *") // Every 24 hours
    public void updateDailyViewCounts() {
        Set<String> keys = redisRepository.getKeys("board:*:viewsLast24Hours");
        for (String key : keys) {
            redisRepository.setViewCount(key, 0L); // Reset daily views
        }
        log.info("Daily view counts updated.");
    }
}