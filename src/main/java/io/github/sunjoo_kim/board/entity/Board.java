package io.github.sunjoo_kim.board.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "boards")
@Getter
@Setter
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

    @JoinColumn(name = "view_count")
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

    public void update(String title, String content) {
    }

    // Builder method to encapsulate creation logic
    public static Board create(String title, String content, Long userId) {
        Board board = new Board();
        board.title = title;
        board.content = content;
        board.author = new User();
        board.author.setId(userId);
        board.createdAt = LocalDateTime.now();
        board.updatedAt = LocalDateTime.now();
        board.viewCount = 0L; // Initialize view count
        return board;
    }

    public long calculateScore(long viewsLast12Hours, long viewsLast1Hour) {
        return viewCount + 2 * viewsLast12Hours + 3 * viewsLast1Hour;
    }
}
