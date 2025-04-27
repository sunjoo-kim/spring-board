package io.github.sunjoo_kim.board.controller;

import io.github.sunjoo_kim.board.dto.*;
import io.github.sunjoo_kim.board.entity.Board;

import io.github.sunjoo_kim.board.entity.User;
import io.github.sunjoo_kim.board.repository.UserRepository;
import io.github.sunjoo_kim.board.service.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    private final GetBoardService getBoardService;
    private final CreateBoardService createBoardService;
    private final DeleteBoardService deleteBoardService;
    private final UpdateBoardService updateBoardService;
    private final ViewEndService viewEndService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<CreateBoardResponse> createBoard(@RequestBody CreateBoardRequest request) {
        User author = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Board board = new Board();
        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        board.setAuthor(author);

        Board created = createBoardService.createBoard(board);
        CreateBoardResponse response = new CreateBoardResponse(
                created.getId(),
                created.getTitle(),
                created.getAuthor().getUsername()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/detail")
    public ResponseEntity<BoardResponse> getBoardById(@RequestBody GetBoardRequest request) {
        Long id = request.getId();
        Long userId = request.getUserId();
        Board board = getBoardService.getBoardById(id,userId);
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
                board.getAuthor().getUsername(),
                board.getViewCount(),
                board.getCreatedAt(),
                board.getUpdatedAt()
        );
    }

    @PostMapping("/view-end")
    public Map<String, Object> viewEnd(@RequestBody BoardViewEndRequest request) {

        viewEndService.viewEnd(request.getId(), request.getUserId());

        Map<String, Object> response = new HashMap<>();
        response.put("result", "View end processed");
        return response;
    }
}
