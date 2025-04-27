package io.github.sunjoo_kim.board.service;

import io.github.sunjoo_kim.board.event.BoardViewEndEvent;
import io.github.sunjoo_kim.board.event.BoardViewStartedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class ViewEndService {
    private final ApplicationEventPublisher eventPublisher;

    public void viewEnd(Long id, Long userId) {
        eventPublisher.publishEvent(new BoardViewEndEvent(id, userId, Instant.now()));
    }
}
