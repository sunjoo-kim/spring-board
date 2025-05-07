package io.github.sunjoo_kim.board.service;

import io.github.sunjoo_kim.board.entity.Comment;
import io.github.sunjoo_kim.board.repository.BoardRepository;
import io.github.sunjoo_kim.board.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateCommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Comment createComment(Long boardId, Long userId, String content) {
        // 게시글 존재 확인
        if (!boardRepository.existsById(boardId)) {
            throw new IllegalArgumentException("Invalid boardId: " + boardId);
        }

        Comment comment = Comment.createWithIds(boardId, userId, content); // Use builder method
        return commentRepository.save(comment);
    }
}
