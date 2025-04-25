package io.github.sunjoo_kim.board.controller;

import io.github.sunjoo_kim.board.dto.BoardResponse;
import io.github.sunjoo_kim.board.dto.CreateBoardRequest;
import io.github.sunjoo_kim.board.dto.CreateBoardResponse;
import io.github.sunjoo_kim.board.dto.UpdateBoardRequest;
import io.github.sunjoo_kim.board.entity.Board;

import io.github.sunjoo_kim.board.service.CreateBoardService;
import io.github.sunjoo_kim.board.service.DeleteBoardService;
import io.github.sunjoo_kim.board.service.GetBoardService;
import io.github.sunjoo_kim.board.service.UpdateBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    private final GetBoardService getBoardService;
    private final CreateBoardService createBoardService;
    private final DeleteBoardService deleteBoardService;
    private final UpdateBoardService updateBoardService;

    @PostMapping
    public ResponseEntity<CreateBoardResponse> createBoard(@RequestBody CreateBoardRequest request) {
        Board board = new Board();
        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        board.setWriter(request.getWriter());

        Board created = createBoardService.createBoard(board);
        CreateBoardResponse response = new CreateBoardResponse(
                created.getId(),
                created.getTitle(),
                created.getWriter()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getBoardById(@PathVariable Long id) {
        Board board = getBoardService.getBoardById(id);
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

    @PutMapping("/{id}")
    public ResponseEntity<BoardResponse> updateBoard(
            @PathVariable Long id,
            @RequestBody UpdateBoardRequest request
    ) {
        Board updated = updateBoardService.updateBoard(id, request);
        return ResponseEntity.ok(convertToResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        deleteBoardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }

    private BoardResponse convertToResponse(Board board) {
        return new BoardResponse(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getWriter(),
                board.getCreatedAt(),
                board.getUpdatedAt()
        );
    }
}
