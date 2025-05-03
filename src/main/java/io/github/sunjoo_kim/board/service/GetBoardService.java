package io.github.sunjoo_kim.board.service;

import io.github.sunjoo_kim.board.entity.Board;
import io.github.sunjoo_kim.board.event.BoardViewCountEvent;
import io.github.sunjoo_kim.board.event.BoardViewStartedEvent;
import io.github.sunjoo_kim.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
public class GetBoardService {

    private final BoardRepository boardRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly = true)
    public Board getBoardById(Long id, Long userId) {
        eventPublisher.publishEvent(new BoardViewStartedEvent(id, userId, Instant.now()));
        return boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found"));
    }
    @Transactional
    public void publishViewCountIncrementEvent(Long boardId, Long userId) {
        System.out.println("publishViewCountIncrementEvent started for boardId: " + boardId);
        eventPublisher.publishEvent(new BoardViewCountEvent(boardId, userId, Instant.now()));
    }
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

}
