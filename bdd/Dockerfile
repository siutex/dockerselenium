FROM alpine/git:latest AS builder
WORKDIR /usr/share/cucumber
RUN git clone https://github.com/siutex/dockerselenium.git

FROM maven:3.9.0-eclipse-temurin-11-alpine as maven_build
RUN apk add curl jq
WORKDIR /usr/share/cucumber/dockerselenium
COPY --from=builder /usr/share/cucumber/dockerselenium ./
# build the app and download dependencies only when these are new (thanks to the cache)
RUN --mount=type=cache,target=/root/.m2  mvn clean package -Dmaven.test.skip=true
# split the built app into multiple layers to improve layer rebuild
RUN mkdir -p target/docker-packaging && cd target/docker-packaging && jar -xf ../selenium-docker-cucumber.jar && jar -xf ../selenium-docker-cucumber-tests.jar
FROM openjdk:11.0-jre
WORKDIR /app
COPY --from=maven_build /usr/share/cucumber/dockerselenium/target/docker-packaging ./
ARG DOCKER_PACKAGING_DIR=/usr/share/cucumber/dockerselenium/target/docker-packaging
#COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/lib /app/lib
#COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/classes /app/classes
#COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/test-classes /app/classes
COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/META-INF /app/META-INF

ENTRYPOINT sh healthcheck.sh