package io.github.sunjoo_kim.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateBoardRequest {
    private String title;
    private String content;
    private String writer;
}
