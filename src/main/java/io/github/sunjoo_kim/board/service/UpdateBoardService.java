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
public class UpdateBoardService {

    private final BoardRepository boardRepository;

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public Board updateBoard(Long id, UpdateBoardRequest request) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found"));

        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        return boardRepository.save(board);
    }

}
