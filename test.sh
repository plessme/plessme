#!/bin/bash
set -e

export access_token=$(\
    curl -X POST https://plessme.bongladesch.com/auth/realms/plessme/protocol/openid-connect/token \
    --user plessme-users:a3c94c7f-f2f0-4b3c-a6a1-37ecf33a02b5 \
    -H 'content-type: application/x-www-form-urlencoded' \
    -d 'username=ken.brucksch@gmail.com&password=password&grant_type=password' | jq --raw-output '.access_token' \
 )

export return=$(\
curl -v -X GET https://plessme.bongladesch.com/users \
  -H "Authorization: Bearer "$access_token
)

echo $return
