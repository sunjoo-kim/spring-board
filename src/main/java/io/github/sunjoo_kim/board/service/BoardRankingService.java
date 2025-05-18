package io.github.sunjoo_kim.board.service;

import io.github.sunjoo_kim.board.dto.BoardDTO;
import io.github.sunjoo_kim.board.entity.Board;
import io.github.sunjoo_kim.board.repository.BoardRepository;
import io.github.sunjoo_kim.board.redis.BoardViewRedisRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@RequiredArgsConstructor
@Service
public class BoardRankingService {

    private final BoardRepository boardRepository;
    private final BoardViewRedisRepository redisRepository;

    @Transactional(readOnly = true)
    public long getBoardScore(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board with id " + boardId + " not found"));
        long viewsLast12Hours = redisRepository.getStayTime("board:staytime:sum12", boardId);
        long viewsLast1Hour = redisRepository.getStayTime("board:staytime:sum1", boardId);
        return board.calculateScore(viewsLast12Hours, viewsLast1Hour);
    }

    @Transactional(readOnly = true)
    public List<BoardDTO> getTop5Boards() {
        List<Board> boards = boardRepository.findAll();
        Map<Long, Long> map = new HashMap<>();
        for (Board board : boards) {
            long viewsLast12Hours = redisRepository.getStayTime("board:staytime:sum12", board.getId());
            long viewsLast1Hour = redisRepository.getStayTime("board:staytime:sum1", board.getId());
            long score = board.calculateScore(viewsLast12Hours, viewsLast1Hour);

            // id: score 쌍으로 이어진 map 만들고, 넣어주세요.
            map.put(board.getId(), score);
        }
        // map을 score 기준으로 내림차순 정렬
        List<Long> sortedList = map.entrySet().stream()
                .sorted(Map.Entry.<Long, Long>comparingByValue().reversed())
                .map(Map.Entry::getKey) // Key (Board ID)만 추출
                .toList(); // List로 변환

        // 정렬된 List에서 상위 5개 Board ID를 가져옵니다.
        List<Long> top5BoardIds = sortedList.stream()
                .limit(5)
                .toList();
        // 상위 5개 Board ID를 사용하여 Board 객체를 가져옵니다.
        List<BoardDTO> top5Boards = new ArrayList<>();
        for (Long boardId : top5BoardIds) {
            Board board = boardRepository.findById(boardId)
                    .orElseThrow(() -> new EntityNotFoundException("Board with id " + boardId + " not found"));
            top5Boards.add(new BoardDTO(board));
        }
        return top5Boards;
    }
}