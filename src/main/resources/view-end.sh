#!/bin/bash
curl -X POST http://localhost:8080/api/boards/view-end \
  -H "Content-Type: application/json" \
  -d '{
    "id": "2",
    "uesrId": "2"
  }'