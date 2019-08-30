#!/usr/bin/env sh
sleep 30s
exec java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
