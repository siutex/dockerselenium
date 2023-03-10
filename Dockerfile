FROM openjdk:8u191-jre-alpine3.8

RUN apk add curl jq

# Workspace
WORKDIR /usr/share/cucumber

# ADD .jar under target from host
# into this image
ADD target/MavenCucumber-1.0-SNAPSHOT.jar 			MavenCucumber-1.0-SNAPSHOT.jar
ADD target/MavenCucumber-1.0-SNAPSHOT-tests.jar 	MavenCucumber-1.0-SNAPSHOT-tests.jar
ADD target/libs							libs


# ADD health check script
ADD healthcheck.sh                      healthcheck.sh

# BROWSER
# HUB_HOST
# MODULE

ENTRYPOINT sh healthcheck.sh