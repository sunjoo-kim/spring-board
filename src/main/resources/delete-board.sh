#!/bin/bash

API_URL="http://localhost:8080/api/boards"
BOARD_ID=3
curl -X DELETE "$API_URL/$BOARD_ID" -H "Content-Type: application/json"