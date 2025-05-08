#!/bin/bash

# Define API endpoint variables
API_URL="http://localhost:8080/api/boards/2/comments"

# Define the JSON payload variables
USER_ID=1
CONTENT="This is a sample comment"

# Construct JSON payload
JSON_PAYLOAD=$(cat <<EOF
{
  "userId": $USER_ID,
  "content": "$CONTENT"
}
EOF
)

# Make the POST request
curl -X POST "$API_URL" \
     -H "Content-Type: application/json" \
     -d "$JSON_PAYLOAD"