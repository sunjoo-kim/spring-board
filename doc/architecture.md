# Architecture

## 구조

Layered Architecture 사용

- Controller
- Service
- Repository
- Entity

## 흐름

Client
→ Controller
→ Service
→ Repository
→ DB

## 설계 이유

- 역할 분리
- 유지보수 용이성
- 테스트 용이성