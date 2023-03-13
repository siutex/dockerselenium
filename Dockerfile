FROM openjdk:19-jdk-alpine3.16

RUN apk add curl jq

# Workspace
WORKDIR /usr/share/cucumber


ADD target/selenium-docker-cucumber.jar         selenium-docker-cucumber.jar
ADD target/selenium-docker-cucumber-tests.jar   selenium-docker-cucumber-tests.jar
ADD target/libs							        libs


# ADD health check script
ADD healthcheck.sh                      healthcheck.sh

# BROWSER
# HUB_HOST
# MODULE

ENTRYPOINT sh healthcheck.sh