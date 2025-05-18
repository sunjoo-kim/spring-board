package io.github.sunjoo_kim.board.dto;

import io.github.sunjoo_kim.board.entity.Board;
import lombok.Getter;

@Getter
public class BoardDTO {
    private Long id;
    private String title;
    private String authorName; // User의 username만 전달

    // 생성자
    public BoardDTO(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.authorName = board.getAuthor().getUsername();
    }
}