package io.github.sunjoo_kim.board.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@Slf4j
public class BoardViewEventListener {

    @EventListener
    public void handleBoardViewStartedEvent(BoardViewStartedEvent event) {
        Long postId = event.getBoardId();
        Long userId = event.getUserId();
        Instant startTime = event.getStartTime();
        log.info("게시글 조회 시작 이벤트 발생: postId={}, userId={}, startTime={}", postId, userId,startTime);
        // TODO: 메트릭 시스템에 이벤트 전송 또는 로깅
    }

    @EventListener
    public void handleBoardViewEndEvent(BoardViewEndEvent event) {
        Long postId = event.getBoardId();
        Long userId = event.getUserId();
        Instant startTime = event.getEndTime();
        log.info("게시글 조회 종료 이벤트 발생: postId={}, userId={}, startTime={}", postId, userId,startTime);
        // TODO: 메트릭 시스템에 이벤트 전송 또는 로깅
    }
}
