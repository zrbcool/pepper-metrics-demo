#!/usr/bin/env bash
COMPONENT="pepper-metrics-demo-server"
docker build -t zrbcool/${COMPONENT}:latest -f Dockerfile .
