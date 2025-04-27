package io.github.sunjoo_kim.board.service;

import io.github.sunjoo_kim.board.entity.Board;
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

    @Transactional
    public Board getBoardById(Long id, Long userId) {
        eventPublisher.publishEvent(new BoardViewStartedEvent(id, userId, Instant.now()));
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found"));

        // 조회수 증가
        board.setViewCount(board.getViewCount() + 1);
        boardRepository.save(board);

        return board;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

}
