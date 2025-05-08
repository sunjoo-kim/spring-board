package io.github.sunjoo_kim.board.service;

import io.github.sunjoo_kim.board.entity.Comment;
import io.github.sunjoo_kim.board.entity.Board;
import io.github.sunjoo_kim.board.entity.User;
import io.github.sunjoo_kim.board.repository.BoardRepository;
import io.github.sunjoo_kim.board.repository.CommentRepository;
import io.github.sunjoo_kim.board.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateCommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Comment createComment(Long boardId, Long userId, String content) {
        // 게시글과 사용자 존재 확인
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid boardId: " + boardId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid userId: " + userId));

        Comment comment = Comment.builder()
                .board(board)
                .author(user)
                .content(content)
                .build();

        return commentRepository.save(comment);
    }
}
