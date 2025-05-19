package io.github.sunjoo_kim.board.controller;

import io.github.sunjoo_kim.board.dto.CreateCommentRequest;
import io.github.sunjoo_kim.board.service.CreateCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards/{boardId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CreateCommentService createCommentService;

    @PostMapping
    public ResponseEntity<Void> createComment(
            @PathVariable Long boardId,
            @Valid @RequestBody CreateCommentRequest request) {
        createCommentService.createComment(boardId, request.getUserId(), request.getContent());
        return ResponseEntity.ok().build();
    }
}
