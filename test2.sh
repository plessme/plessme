#!/bin/bash
set -e

curl -X POST -H "Content-Type: application/json" -d '{"email":"ken.brucksch@gmail.com","password":"password","firstName":"Ken","lastName":"Brucksch"}' https://plessme.bongladesch.com/users
