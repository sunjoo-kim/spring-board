package io.github.sunjoo_kim.board.dto;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class BoardResponse {
    private final String title;
    private final String authorName;
    private final Long viewCount;

    public BoardResponse(String title, String authorName, Long viewCount) {
        this.title = title;
        this.authorName = authorName;
        this.viewCount = viewCount;
    }
}
