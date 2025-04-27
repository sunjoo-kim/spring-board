package io.github.sunjoo_kim.board.dto;

import io.github.sunjoo_kim.board.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateBoardResponse {
    private Long id;
    private String title;
    private String authorName;
}