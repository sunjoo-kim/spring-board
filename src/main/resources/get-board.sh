#!/bin/bash

curl -X POST "http://localhost:8080/api/boards/detail" \
     -H "Content-Type: application/json" \
     -d '{
           "id": "2",
           "userId": "2"
         }'
