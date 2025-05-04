package io.github.sunjoo_kim.board.service;

import io.github.sunjoo_kim.board.entity.Board;
import io.github.sunjoo_kim.board.event.BoardViewCountEvent;
import io.github.sunjoo_kim.board.event.BoardViewStartedEvent;
import io.github.sunjoo_kim.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class GetBoardService {

    private final BoardRepository boardRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional(readOnly = true)
    public Board getBoardById(Long id, Long userId) {
        final Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board with id " + id + " not found"));
        Instant now = Instant.now();
        eventPublisher.publishEvent(new BoardViewStartedEvent(id, userId, now));
        eventPublisher.publishEvent(new BoardViewCountEvent(id, userId, now));
        return board;
    }
    @Transactional(readOnly = true)
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

}
