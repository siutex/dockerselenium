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
RUN mkdir -p target/docker-packaging && cd target/docker-packaging && jar -xf ../selenium-docker-cucumber*.jar

########JRE run stage########
FROM maven_build
WORKDIR /usr/share/cucumber/dockerselenium

#copy built app layer by layer
ARG DOCKER_PACKAGING_DIR=/usr/share/cucumber/dockerselenium/target/docker-packaging
#COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/BOOT-INF/lib /usr/share/cucumber/dockerselenium/lib
#COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/BOOT-INF/classes /usr/share/cucumber/dockerselenium/classes
#COPY --from=maven_build ${DOCKER_PACKAGING_DIR}/META-INF /usr/share/cucumber/dockerselenium/META-INF

ENTRYPOINT sh healthcheck.sh