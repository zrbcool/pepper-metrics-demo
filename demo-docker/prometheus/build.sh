#!/usr/bin/env bash
COMPONENT="pepper-metrics-demo-prom"
sudo docker build -t zrbcool/${COMPONENT}:latest -f Dockerfile .
