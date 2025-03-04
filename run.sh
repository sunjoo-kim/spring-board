#!/bin/bash

# Docker Compose로 MariaDB 실행
docker-compose up -d

# MariaDB가 완전히 시작될 때까지 대기
echo "Waiting for MariaDB to start..."
sleep 10

# Spring Boot 애플리케이션 빌드 및 실행
./gradlew build
java -jar build/libs/board-0.0.1-SNAPSHOT.jar
