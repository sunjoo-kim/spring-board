package io.github.sunjoo_kim.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardViewEndRequest {
    private Long id;
    private Long userId;
    private String time;
}