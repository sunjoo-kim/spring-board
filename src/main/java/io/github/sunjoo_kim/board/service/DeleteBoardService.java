package io.github.sunjoo_kim.board.service;

import io.github.sunjoo_kim.board.entity.Board;
import io.github.sunjoo_kim.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class DeleteBoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found"));

        boardRepository.delete(board);
    }
}
