package io.github.sunjoo_kim.board.controller;

import io.github.sunjoo_kim.board.dto.*;
import io.github.sunjoo_kim.board.entity.Board;
import io.github.sunjoo_kim.board.entity.User;
import io.github.sunjoo_kim.board.repository.UserRepository;
import io.github.sunjoo_kim.board.service.DeleteBoardService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@Slf4j
public class DeleteBoardController {
    private final DeleteBoardService deleteBoardService;

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        deleteBoardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }
}
