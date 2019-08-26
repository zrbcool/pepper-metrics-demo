#!/usr/bin/env bash
CUR_DIR=`pwd`
PRJ_ROOT="../../"
cd ${PRJ_ROOT}/demo-app
mvn clean package -DskipTests
cd ${CUR_DIR}
rm -f *.jar
cp ${PRJ_ROOT}/demo-app/demo-server/target/*.jar .
COMPONENT="pepper-metrics-demo-server"
sudo docker build -t zrbcool/${COMPONENT}:latest -f Dockerfile .