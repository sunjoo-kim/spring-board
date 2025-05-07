#!/bin/bash

# Define API endpoint variables
BASE_URL="http://localhost:8080/api/boards"
BOARD_ID=1
API_URL="$BASE_URL/$BOARD_ID/comments"

# Define the JSON payload variables
USER_ID=2
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
RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" -X POST "$API_URL" \
     -H "Content-Type: application/json" \
     -d "$JSON_PAYLOAD")

# Check the response status
if [ "$RESPONSE" -eq 201 ]; then
  echo "Comment created successfully."
else
  echo "Failed to create comment. HTTP Status Code: $RESPONSE"
fi
