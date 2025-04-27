package io.github.sunjoo_kim.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetBoardRequest {
    private Long id;
    private Long userId;
}