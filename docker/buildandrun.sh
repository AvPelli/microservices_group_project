#!/usr/bin/env bash

echo -e "[INF] \t Building and starting containers (might take some time)"
docker-compose up -d --build --no-color >& docker-compose.log
echo -e "[INF] \t Docker-compose finished, logs written to docker-compose.log"
