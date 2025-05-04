package io.github.sunjoo_kim.board.controller;

import io.github.sunjoo_kim.board.dto.*;
import io.github.sunjoo_kim.board.entity.Board;

import io.github.sunjoo_kim.board.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@Slf4j
public class UpdateBoardController {
    private final UpdateBoardService updateBoardService;

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponse> updateBoard(
            @PathVariable Long id,
            @RequestBody UpdateBoardRequest request
    ) {
        Board updated = updateBoardService.updateBoard(id, request);
        return ResponseEntity.ok(convertToResponse(updated));
    }

    private BoardResponse convertToResponse(Board board) {
        return new BoardResponse(
                board.getTitle(),
                board.getAuthor().getUsername(),
                board.getViewCount()
        );
    }

}
