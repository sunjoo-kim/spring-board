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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
@Slf4j
public class CreateBoardController {
    private final CreateBoardService createBoardService;
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
}
