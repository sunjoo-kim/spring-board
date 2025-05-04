package io.github.sunjoo_kim.board.repository;

import io.github.sunjoo_kim.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom{
}

