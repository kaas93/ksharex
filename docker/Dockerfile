FROM gradle:7-jdk8 as build

WORKDIR /opt/sharex

RUN gradle :installDist

FROM openjdk:8-jdk-alpine

EXPOSE 8080

WORKDIR /opt/sharex/build/install/ksharex/bin/

CMD chmod +x ./ksharex

ENTRYPOINT ./ksharex