package io.github.sunjoo_kim.board.controller;

import io.github.sunjoo_kim.board.dto.*;
import io.github.sunjoo_kim.board.entity.Board;
import io.github.sunjoo_kim.board.entity.User;
import io.github.sunjoo_kim.board.repository.UserRepository;
import io.github.sunjoo_kim.board.service.CreateBoardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@Slf4j
public class CreateBoardController {
    private final CreateBoardService createBoardService;

    @PostMapping
    public ResponseEntity<Board> createBoard(@Validated @RequestBody CreateBoardRequest request) {
        Board board = createBoardService.createBoard(
                request.getTitle(),
                request.getContent(),
                request.getUserId()
        );
        return ResponseEntity.ok(board);
    }
}
