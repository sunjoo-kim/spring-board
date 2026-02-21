# Spring Board

Spring Boot 기반 게시판 API 서버입니다.  
RESTful API 설계, JPA 기반 데이터 처리, Docker 환경 구성, GitHub Actions를 활용한 CI를 적용한 개인 백엔드 프로젝트입니다.

---

## 1. 프로젝트 목적

- Spring Boot 기반 백엔드 아키텍처 설계 경험
- REST API 설계 및 구현
- JPA를 활용한 ORM 데이터 처리
- Docker 기반 실행 환경 구성
- GitHub Actions를 통한 CI 자동화

---

## 2. 기술 스택

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- Gradle

### Database
- MariaDB

### Infra
- Docker
- Docker Compose

### CI/CD
- GitHub Actions

---

## 3. 프로젝트 구조


spring-board
├─ src/main/java
│ ├─ controller
│ ├─ service
│ ├─ repository
│ └─ entity
│
├─ infra
│ ├─ docker
│ └─ db
│
├─ docs
│
└─ .github/workflows


---

## 4. 아키텍처


Controller
↓
Service
↓
Repository
↓
Database


Layered Architecture 기반으로 설계했습니다.

---

## 5. 주요 기능

### 게시글

- 게시글 생성
- 게시글 조회
- 게시글 수정
- 게시글 삭제

---

## 6. 실행 방법

### 1. clone


git clone https://github.com/sunjoo-kim/spring-board.git


### 2. docker 실행


docker-compose up -d


### 3. application 실행


./gradlew bootRun


---

## 7. API 예시

### 게시글 생성


POST /posts


### 게시글 조회


GET /posts/{id}


---

## 8. CI

GitHub Actions를 사용하여

- build 자동화
- 코드 검증 자동화

구성했습니다.

---

## 9. 향후 개선 계획

---

## 10. 프로젝트를 통해 학습한 내용
