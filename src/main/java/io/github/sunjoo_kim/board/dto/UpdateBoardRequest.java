package io.github.sunjoo_kim.board.dto;

import lombok.Getter;

@Getter
public class UpdateBoardRequest {
    private String title;
    private String content;
}
