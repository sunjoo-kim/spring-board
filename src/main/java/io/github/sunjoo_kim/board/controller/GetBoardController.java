package io.github.sunjoo_kim.board.controller;

import io.github.sunjoo_kim.board.dto.*;
import io.github.sunjoo_kim.board.entity.Board;
import io.github.sunjoo_kim.board.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@Slf4j
public class GetBoardController {
    private final GetBoardService getBoardService;
    private final ViewEndService viewEndService;

    @PostMapping("/detail")
    public ResponseEntity<BoardResponse> getBoardById(@RequestBody GetBoardRequest request) {
        Board board = getBoardService.getBoardById(request.getId(),request.getUserId());
        return ResponseEntity.ok(convertToResponse(board));
    }

    @GetMapping
    public ResponseEntity<List<BoardResponse>> getAllBoards() {
        List<Board> boards = getBoardService.getAllBoards();
        List<BoardResponse> responses = boards.stream()
                .map(this::convertToResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }
    @PostMapping("/view-end")
    public ResponseEntity<Void> viewEnd(@RequestBody BoardViewEndRequest request) {
        log.debug("게시글 조회 종료 요청: boardId={}, userId={}", request.getId(), request.getUserId());
        viewEndService.publishViewEndTimeEvent(request.getId(), request.getUserId());
        return ResponseEntity.ok().build();
    }


    private BoardResponse convertToResponse(Board board) {
        return new BoardResponse(
                board.getTitle(),
                board.getAuthor().getUsername(),
                board.getViewCount()
        );
    }

}
