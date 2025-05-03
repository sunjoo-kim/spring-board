package io.github.sunjoo_kim.board.event;

import io.github.sunjoo_kim.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ViewCountIncrementListener {

    private final BoardRepository boardRepository;

    @TransactionalEventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleViewCountIncrement(BoardViewCountEvent event) {
        System.out.println("handleViewCountIncrement started for boardId: " + event.getBoardId());
        boardRepository.incrementViewCount(event.getBoardId());

    }
}