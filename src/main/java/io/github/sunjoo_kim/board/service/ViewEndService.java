package io.github.sunjoo_kim.board.service;


import io.github.sunjoo_kim.board.event.BoardViewEndEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.time.Instant;

@RequiredArgsConstructor
@Service
public class ViewEndService {
    private final ApplicationEventPublisher eventPublisher;

    public void publishViewEndTimeEvent(Long id, Long userId) {
        eventPublisher.publishEvent(new BoardViewEndEvent(id, userId, Instant.now()));
    }

}
