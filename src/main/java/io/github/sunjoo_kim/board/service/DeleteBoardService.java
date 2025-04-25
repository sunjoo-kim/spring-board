package io.github.sunjoo_kim.board.service;

import io.github.sunjoo_kim.board.dto.UpdateBoardRequest;
import io.github.sunjoo_kim.board.entity.Board;
import io.github.sunjoo_kim.board.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeleteBoardService {

    private final BoardRepository boardRepository;

    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
    }
    public void deleteBoard(Long id) {
        Board board = getBoardById(id);
        boardRepository.delete(board);
    }
}
