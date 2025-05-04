package io.github.sunjoo_kim.board.event;

import io.github.sunjoo_kim.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class ViewCountIncrementListener {

    private final BoardRepository boardRepository;

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleViewCountIncrement(BoardViewCountEvent event) {
        log.debug("handleViewCountIncrement started for boardId: " + event.getBoardId());
        boardRepository.incrementViewCount(event.getBoardId());

    }
}