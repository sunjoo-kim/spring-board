package io.github.sunjoo_kim.board.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class BoardStayTimeRecorder {

    private final RedisTemplate<String, String> redisTemplate;

    public void recordStay(Long boradId, Long userId, long staySeconds) {
        long now = Instant.now().getEpochSecond();

        String logKey = "board:staytime:log";
        String sumKey = "board:staytime:sum";

        // (1) 체류 기록 저장 (timestamp 기준)
        String member = boradId + ":" + userId;
        redisTemplate.opsForZSet().add(logKey, member, now);

        // (2) 총 체류시간 업데이트
        redisTemplate.opsForZSet().incrementScore(sumKey, boradId.toString(), staySeconds);
    }

    public void cleanUpOldStayLogs() {
        long now = Instant.now().getEpochSecond();
        long expireBefore = now - (24 * 60 * 60);

        String logKey = "board:staytime:log";
        String sumKey = "board:staytime:sum";

        // (1) 24시간 넘은 체류 기록 조회
        Set<String> expiredLogs = redisTemplate.opsForZSet()
                .rangeByScore(logKey, 0, expireBefore);

        if (expiredLogs != null) {
            for (String member : expiredLogs) {
                String[] parts = member.split(":");
                String boardId = parts[0];

                // (2) 이 게시글 체류시간에서 적당량 차감 (대략적인 값, or 기록 따로 저장하면 정확 가능)
                // 여기서는 예시로 그냥 고정 차감 (실제는 staySeconds 기록 필요)

                redisTemplate.opsForZSet().incrementScore(sumKey, boardId, -30); // 30초 감소 예시

                // (3) 로그 삭제
                redisTemplate.opsForZSet().remove(logKey, member);
            }
        }
    }
}
