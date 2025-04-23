package io.github.sunjoo_kim.board.controller;

import io.github.sunjoo_kim.board.dto.CreateBoardRequest;
import io.github.sunjoo_kim.board.dto.CreateBoardResponse;
import io.github.sunjoo_kim.board.entity.Board;

import io.github.sunjoo_kim.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<CreateBoardResponse> createBoard(@RequestBody CreateBoardRequest request) {
        Board board = new Board();
        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        board.setWriter(request.getWriter());

        Board created = boardService.createBoard(board);
        CreateBoardResponse response = new CreateBoardResponse(
                created.getId(),
                created.getTitle(),
                created.getWriter()
        );

        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoardById(id));
    }

    @GetMapping
    public ResponseEntity<List<Board>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody Board boardDetails) {
        return ResponseEntity.ok(boardService.updateBoard(id, boardDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok().build();
    }
}
