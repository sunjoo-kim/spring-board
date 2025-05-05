package io.github.sunjoo_kim.board.controller;

import io.github.sunjoo_kim.board.entity.Board;
import io.github.sunjoo_kim.board.service.BoardRankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards/ranking")
@RequiredArgsConstructor
public class BoardRankingController {

    private final BoardRankingService boardRankingService;

    @GetMapping("/{id}/score")
    public ResponseEntity<Long> getBoardScore(
            @PathVariable Long id) {
        long score = boardRankingService.getBoardScore(id);
        return ResponseEntity.ok(score);
    }

    @GetMapping("/top5")
    public ResponseEntity<List<Board>> getTop5Boards(){
        List<Board> top5Boards = boardRankingService.getTop5Boards();
        return ResponseEntity.ok(top5Boards);
    }
}