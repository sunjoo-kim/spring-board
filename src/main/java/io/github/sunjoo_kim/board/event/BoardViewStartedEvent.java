package io.github.sunjoo_kim.board.event;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;

@Getter
@RequiredArgsConstructor
public class BoardViewStartedEvent {
    private final Long boardId;
    private final Long userId;
    private final Instant startTime;
}
