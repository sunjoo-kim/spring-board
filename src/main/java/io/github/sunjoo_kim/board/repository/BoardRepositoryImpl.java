package io.github.sunjoo_kim.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.github.sunjoo_kim.board.entity.QBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class BoardRepositoryImpl implements BoardRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    @Transactional
    public void incrementViewCount(Long boardId) {
        QBoard board = QBoard.board;

        queryFactory.update(board)
                .set(board.viewCount, board.viewCount.add(1))
                .where(board.id.eq(boardId))
                .execute();
    }

}