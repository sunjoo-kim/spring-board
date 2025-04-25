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
public class CreateBoardService {

    private final BoardRepository boardRepository;

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

}
