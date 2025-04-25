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
public class GetBoardService {

    private final BoardRepository boardRepository;

    public Board getBoardById(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found"));
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

}
