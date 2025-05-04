package io.github.sunjoo_kim.board.service;


import io.github.sunjoo_kim.board.event.BoardViewEndEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
@Service
public class ViewEndService {
    private final ApplicationEventPublisher eventPublisher;

    public void publishViewEndTimeEvent(Long id, Long userId) {
        log.debug("게시글 조회 종료 이벤트 발행: boardId={}, userId={}", id, userId);
        eventPublisher.publishEvent(new BoardViewEndEvent(id, userId, Instant.now()));
    }

}
