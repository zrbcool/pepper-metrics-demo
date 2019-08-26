#!/usr/bin/env bash
#rm -rf pepper-metrics
#git clone https://github.com/zrbcool/pepper-metrics.git
COMPONENT="pepper-metrics-demo-grafana"
sudo docker build -t zrbcool/${COMPONENT}:latest -f Dockerfile .