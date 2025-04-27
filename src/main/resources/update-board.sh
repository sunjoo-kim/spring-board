#!/bin/bash

curl -X PUT "http://localhost:8080/api/boards/2" \
     -H "Content-Type: application/json" \
     -d '{
           "title": "게시글 제목 수정",
           "content": "게시글 본문 수정"
         }'
