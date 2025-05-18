package io.github.sunjoo_kim.board.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "boards")
@Getter
@Setter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob // Large Object
    private String content;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", unique = true)
    private User author;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @Column(name = "view_count")
    private Long viewCount;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        viewCount = 0L;
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


    // 빌더 패턴 적용
    @Builder
    public Board(Long id, String title, String content, User author, LocalDateTime createdAt, LocalDateTime updatedAt, Long viewCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.updatedAt = updatedAt;
        this.viewCount = viewCount != null ? viewCount : 0L;
    }

    // 정적 메서드를 통한 빌더 생성 (User ID)
    public static Board createWithUserId(String title, String content, Long userId) {
        return Board.builder()
                .title(title)
                .content(content)
                .author(User.builder().id(userId).build())
                .viewCount(0L)
                .createdAt(LocalDateTime.now())
                .build();
    }
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    public long calculateScore(long viewsLast12Hours, long viewsLast1Hour) {
        return viewCount + 2 * viewsLast12Hours + 3 * viewsLast1Hour;
    }

    @Builder
    public Board(Long id) {
        this.id = id;
    }
}
