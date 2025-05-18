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

    public void recordStay(Long boardId, Long userId, long staySeconds) {

        long now = Instant.now().getEpochSecond();

        String logKey1 = "board:staytime:log1";
        String logKey12 = "board:staytime:log12";
        String sumKey1 = "board:staytime:sum1";
        String sumKey12 = "board:staytime:sum12";

        // (1) 체류 기록 저장 (timestamp 기준)
        String member = boardId + ":" + userId + ":" + staySeconds;
        redisTemplate.opsForZSet().add(logKey1, member, now);
        redisTemplate.opsForZSet().add(logKey12, member, now);

        // (2) 각 시간대 총 체류시간 업데이트
        redisTemplate.opsForZSet().incrementScore(sumKey1, boardId.toString(), staySeconds);
        redisTemplate.opsForZSet().incrementScore(sumKey12, boardId.toString(), staySeconds);


    }

    public void cleanUpOldStayLogs() {
        long now = Instant.now().getEpochSecond();
        // (1) 1시간, 12시간 넘은 체류 기록 정리
//        cleanUpLogs("board:staytime:log", "board:staytime:sum_1h", now - 3600); // 1시간
//        cleanUpLogs("board:staytime:log", "board:staytime:sum_12h", now - 43200); // 12시간
        cleanUpLogs("board:staytime:log1", "board:staytime:sum1", now - 180 , true ); // 1시간
        cleanUpLogs("board:staytime:log12", "board:staytime:sum12", now - 240, true); // 12시간
    }

    private void cleanUpLogs(String logKey, String sumKey, long expireBefore,boolean deleteLog) {
        Set<String> expiredLogs = redisTemplate.opsForZSet().rangeByScore(logKey, 0, expireBefore);

        if (expiredLogs != null) {
            for (String member : expiredLogs) {
                String[] parts = member.split(":");
                String boardId = parts[0];
                long staySeconds = Long.parseLong(parts[2]);

                // (2) 체류시간 차감
                redisTemplate.opsForZSet().incrementScore(sumKey, boardId, -staySeconds);

                // (3) 로그 삭제
                if (deleteLog) {
                    redisTemplate.opsForZSet().remove(logKey, member);
                }

            }
        }
    }
}
