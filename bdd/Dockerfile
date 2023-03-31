FROM alpine/git:latest AS builder
WORKDIR /usr/share/cucumber
RUN git clone https://github.com/siutex/dockerselenium.git

FROM maven:3.9.0-eclipse-temurin-11-alpine as maven_build
RUN apk add curl jq
WORKDIR /usr/share/cucumber/dockerselenium
COPY --from=builder /usr/share/cucumber/dockerselenium ./
# build the app and download dependencies only when these are new (thanks to the cache)
RUN --mount=type=cache,target=/root/.m2  mvn clean install -DskipTests
ENTRYPOINT sh healthcheck.sh