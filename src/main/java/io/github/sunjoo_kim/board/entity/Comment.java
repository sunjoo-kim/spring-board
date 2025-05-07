package io.github.sunjoo_kim.board.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
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

    @Builder
    public Comment(Board board, User author, String content) {
        this.board = board;
        this.author = author;
        this.content = content;
    }

    // 정적 메서드를 통한 빌더 생성 (Board ID, User ID)
    public static Comment createWithIds(Long boardId, Long userId, String content) {
        return Comment.builder()
                .board(Board.builder().id(boardId).build())
                .author(User.builder().id(userId).build())
                .content(content)
                .build();
    }
}