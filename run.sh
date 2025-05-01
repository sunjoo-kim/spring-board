#!/bin/bash

# Spring Boot 애플리케이션 빌드 및 실행
./gradlew build
java -jar build/libs/board-0.0.1-SNAPSHOT.jar
