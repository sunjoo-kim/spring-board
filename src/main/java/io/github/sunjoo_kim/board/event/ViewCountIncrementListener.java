package io.github.sunjoo_kim.board.event;

import io.github.sunjoo_kim.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.event.TransactionPhase;

@Component
@RequiredArgsConstructor
public class ViewCountIncrementListener {

    private final BoardRepository boardRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleViewCountIncrement(BoardViewCountEvent event) {
        System.out.println("Event  started for boardId: " + event.getBoardId());

        boardRepository.incrementViewCount(event.getBoardId());
        System.out.println("Event received for boardId: " + event.getBoardId());
    }
}