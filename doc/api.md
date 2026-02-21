# API Specification

## 게시글 생성

POST /posts

request

{
"title": "제목",
"content": "내용"
}

response

{
"id": 1
}

---

## 게시글 조회

GET /posts/{id}

response

{
"id": 1,
"title": "제목",
"content": "내용"
}

---

## 게시글 수정

PUT /posts/{id}

---

## 게시글 삭제

DELETE /posts/{id}