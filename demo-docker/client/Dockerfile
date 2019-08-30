FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
COPY wait-for-it.sh wait-for-it.sh
#RUN echo "https://mirror.tuna.tsinghua.edu.cn/alpine/v3.4/main/" > /etc/apk/repositories
#
#RUN apk update \
#        && apk upgrade \
#        && apk add --no-cache bash \
#        bash-doc \
#        bash-completion \
#        && rm -rf /var/cache/apk/* \
#        && /bin/bash

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
