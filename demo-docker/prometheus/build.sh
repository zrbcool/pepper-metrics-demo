#!/usr/bin/env bash
COMPONENT="pepper-metrics-demo-prom"
docker build -t zrbcool/${COMPONENT}:latest -f Dockerfile .
