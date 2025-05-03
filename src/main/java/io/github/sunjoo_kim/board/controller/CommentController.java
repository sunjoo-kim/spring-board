package io.github.sunjoo_kim.board.controller;


import io.github.sunjoo_kim.board.dto.CreateCommentRequest;
import io.github.sunjoo_kim.board.entity.Board;
import io.github.sunjoo_kim.board.entity.Comment;
import io.github.sunjoo_kim.board.entity.User;
import io.github.sunjoo_kim.board.repository.BoardRepository;
import io.github.sunjoo_kim.board.repository.CommentRepository;
import io.github.sunjoo_kim.board.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Void> createComment(@Valid @RequestBody CreateCommentRequest request) {

        // Validate and fetch the board
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> new EntityNotFoundException("Board not found"));

        // Validate and fetch the user (author)
        User author = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Create and save the comment
        Comment comment = new Comment();
        comment.setContent(request.getContent());
        comment.setBoard(board);
        comment.setAuthor(author);
        commentRepository.save(comment);

        return ResponseEntity.ok().build();
    }
}