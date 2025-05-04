package io.github.sunjoo_kim.board.event;

import io.github.sunjoo_kim.board.redis.BoardStayTimeRecorder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
@Slf4j
public class BoardViewEventListener {
    private final RedisTemplate<String, String> redisTemplate;
    public final BoardStayTimeRecorder boardStayTimeRecorder;
    @EventListener
    public void handleBoardViewStartedEvent(BoardViewStartedEvent event) {
        Long boardId = event.getBoardId();
        Long userId = event.getUserId();
        Instant startTime = event.getStartTime();
        // Redis에 저장할 key
        String key = "board:view:start:" + boardId + ":" + userId;
        String value = String.valueOf(startTime.getEpochSecond());
        redisTemplate.opsForValue().set(key, value);
    }

    @EventListener
    public void handleBoardViewEndEvent(BoardViewEndEvent event) {
        Long boardId = event.getBoardId();
        Long userId = event.getUserId();
        String key = "board:view:start:" + boardId + ":" + userId;
        String value = redisTemplate.opsForValue().get(key);
        log.debug("게시글 조회 종료 이벤트 수신: boardId={}, userId={}", boardId, userId);

        if (value != null) {
            // Redis에 저장된 시간 (초 단위 Unix timestamp)
            long savedStartTime = Long.parseLong(value);

            // 현재 시간과 저장된 시간 차이를 계산 (현재 시간 - 저장된 시간)
            long currentTime = Instant.now().getEpochSecond();
            long durationInSeconds = currentTime - savedStartTime;  // 체류 시간 계산 (초 단위)

            boardStayTimeRecorder.recordStay(boardId, userId, durationInSeconds);

            log.debug("게시글 조회 체류 시간 기록: boardId={}, userId={}, durationInSeconds={}", boardId, userId, durationInSeconds);
        } else {
            log.warn("Redis에 저장된 조회 시작 시간이 없습니다. boardId={}, userId={}", boardId, userId);
        }
    }
}
