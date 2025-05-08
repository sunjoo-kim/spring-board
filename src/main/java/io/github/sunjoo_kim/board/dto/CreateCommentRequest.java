package io.github.sunjoo_kim.board.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentRequest {

    @NotNull(message = "userId는 필수입니다.")
    @Positive(message = "userId는 양수여야 합니다.")
    private Long userId; // 댓글 작성자 ID

    @NotBlank(message = "댓글 내용은 비어 있을 수 없습니다.")
    @Size(max = 1000, message = "댓글은 1000자 이내여야 합니다.")
    private String content;   // 댓글 내용
}

