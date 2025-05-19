package io.github.sunjoo_kim.board.controller;

import io.github.sunjoo_kim.board.dto.BoardViewEndRequest;
import io.github.sunjoo_kim.board.service.ViewEndService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/boards/view-end")
@RequiredArgsConstructor
@Slf4j
public class BoardViewEndController {
    private final ViewEndService viewEndService;

    @PostMapping
    public ResponseEntity<Void> BoardViewEnd(@RequestBody BoardViewEndRequest request) {
        log.debug("게시글 조회 종료 요청: boardId={}, userId={}", request.getId(), request.getUserId());
        viewEndService.publishViewEndTimeEvent(request.getId(), request.getUserId());
        return ResponseEntity.ok().build();
    }
}