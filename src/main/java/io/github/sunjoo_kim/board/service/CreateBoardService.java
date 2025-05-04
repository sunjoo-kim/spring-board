package io.github.sunjoo_kim.board.service;

import io.github.sunjoo_kim.board.entity.Board;
import io.github.sunjoo_kim.board.entity.User;
import io.github.sunjoo_kim.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateBoardService {

    private final BoardRepository boardRepository;
    @Transactional
    public Board createBoard(String title, String content, Long userId) {
        Board board = Board.create(title, content, userId); // Use builder method
        return boardRepository.save(board);
    }
}
