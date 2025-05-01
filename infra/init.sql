-- 테이블 생성

CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS boards (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    author_id BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    view_count BIGINT NOT NULL DEFAULT 0,
    FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE SET NULL
);

-- 테스트 데이터 삽입

-- User 테이블에 테스트 데이터 추가
INSERT INTO users (username, password, email)
VALUES
    ('john_doe', 'password123', 'john.doe@example.com'),
    ('jane_smith', 'password456', 'jane.smith@example.com');

-- Board 테이블에 테스트 데이터 추가
INSERT INTO boards (title, content, author_id, created_at, updated_at,view_count)
VALUES
    ('첫 번째 게시물', '첫 번째 게시물 내용입니다.', 1,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0),
    ('두 번째 게시물', '두 번째 게시물 내용입니다.', 2,CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

