package io.github.sunjoo_kim.board.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 댓글 내용
    private String content;

    // 댓글 작성자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    // 댓글이 달린 게시글
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    // 생성일시
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // 수정일시
    private LocalDateTime updatedAt;

    // 생성 시각 자동 세팅
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // 수정 시각 자동 세팅
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}