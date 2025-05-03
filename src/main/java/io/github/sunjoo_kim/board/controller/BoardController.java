package io.github.sunjoo_kim.board.controller;

import io.github.sunjoo_kim.board.dto.*;
import io.github.sunjoo_kim.board.entity.Board;

import io.github.sunjoo_kim.board.entity.User;
import io.github.sunjoo_kim.board.repository.UserRepository;
import io.github.sunjoo_kim.board.service.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@Slf4j
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
        Board board = getBoardService.getBoardById(request.getId(),request.getUserId());
        getBoardService.publishViewCountIncrementEvent(request.getId(), request.getUserId());
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
                board.getTitle(),
                board.getAuthor().getUsername(),
                board.getViewCount()
        );
    }

    @PostMapping("/view-end")
    public ResponseEntity<Void> viewEnd(@RequestBody BoardViewEndRequest request) {
        log.warn("게시글 조회 종료 요청: boardId={}, userId={}", request.getId(), request.getUserId());
        viewEndService.publishViewEndTimeEvent(request.getId(), request.getUserId());
        return ResponseEntity.ok().build();
    }
}
