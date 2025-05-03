#!/bin/bash

# Define the API endpoint
API_URL="http://localhost:8080/api/comments"

# Define the JSON payload
JSON_PAYLOAD=$(cat <<EOF
{
  "boardId": 1,
  "userId": 1,
  "content": "This is a sample comment"
}
EOF
)

# Make the POST request
curl -X POST "$API_URL" \
     -H "Content-Type: application/json" \
     -d "$JSON_PAYLOAD"