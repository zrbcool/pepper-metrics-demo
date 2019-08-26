#!/usr/bin/env bash
COMPONENT="pepper-metrics-demo-client"
VERSION=$1
sudo docker push zrbcool/${COMPONENT}:latest
sudo docker tag zrbcool/${COMPONENT}:latest zrbcool/${COMPONENT}:${VERSION}
sudo docker push zrbcool/${COMPONENT}:${VERSION}