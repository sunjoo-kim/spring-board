package io.github.sunjoo_kim.board.service;

import io.github.sunjoo_kim.board.entity.Board;
import io.github.sunjoo_kim.board.repository.BoardRepository;
import io.github.sunjoo_kim.board.redis.BoardViewRedisRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardRankingService {

    private final BoardRepository boardRepository;
    private final BoardViewRedisRepository redisRepository;

    @Transactional(readOnly = true)
    public long getBoardScore(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board with id " + boardId + " not found"));
        long viewsLast12Hours = redisRepository.getViewCount("board:" + boardId + ":viewsLast24Hours");
        long viewsLast1Hour = redisRepository.getViewCount("board:" + boardId + ":viewsLast1Hour");
        return board.calculateScore(viewsLast12Hours, viewsLast1Hour);
    }

    @Transactional(readOnly = true)
    public List<Board> getTop5Boards() {
        List<Board> boards = boardRepository.findAll();

        boards.sort(Comparator.comparingLong((Board board) -> {
            long totalViews = redisRepository.getViewCount("board:" + board.getId() + ":totalViews");
            long viewsLast12Hours = redisRepository.getViewCount("board:" + board.getId() + ":viewsLast24Hours");
            long viewsLast1Hour = redisRepository.getViewCount("board:" + board.getId() + ":viewsLast1Hour");
            return board.calculateScore(viewsLast12Hours, viewsLast1Hour);       }).reversed().thenComparing(Board::getCreatedAt));

        return boards.stream().limit(5).toList();
    }
}