package io.github.sunjoo_kim.board.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {
    private String content;   // 댓글 내용
    private Long boardId;     // 댓글이 달릴 게시글 ID
    private Long userId; // 댓글 작성자 ID
}

