FROM alpine/git:latest as gitreq
WORKDIR /usr/share/cucumber
RUN git clone https://github.com/siutex/dockerselenium.git

FROM maven:3.9.0-eclipse-temurin-17-alpine as maven
# Workspace


RUN git clone https://github.com/siutex/dockerselenium.git

RUN mvn