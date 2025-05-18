#!/bin/bash

curl -X GET "http://localhost:8080/api/boards/ranking/top5" \
     -H "Content-Type: application/json"