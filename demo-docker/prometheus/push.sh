#!/usr/bin/env bash
COMPONENT="pepper-metrics-demo-prom"
VERSION=$1
docker push zrbcool/$COMPONENT:latest
docker tag zrbcool/$COMPONENT:latest zrbcool/$COMPONENT:$VERSION
docker push zrbcool/$COMPONENT:$VERSION
