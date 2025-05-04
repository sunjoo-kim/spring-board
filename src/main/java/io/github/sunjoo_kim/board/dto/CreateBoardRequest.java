package io.github.sunjoo_kim.board.dto;

import io.github.sunjoo_kim.board.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBoardRequest {

    @NotBlank(message = "Title must not be blank")
    @Size(max = 255, message = "Title must not exceed 255 characters")
    private String title;

    @NotBlank(message = "Content must not be blank")
    @Size(max = 5000, message = "Content must not exceed 5000 characters")
    private String content;

    @NotNull(message = "User ID must not be null")
    private Long userId;
}
