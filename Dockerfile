FROM openjdk:8-jdk-alpine3.8

RUN apk add --no-cache bash

COPY ./target/universal/pawn-tour-0.1.tgz /usr/local/app/pawn-tour-0.1.tgz

WORKDIR /usr/local/app

RUN tar xf pawn-tour-0.1.tgz
RUN rm pawn-tour-0.1.tgz

WORKDIR /usr/local/app/pawn-tour-0.1

ENTRYPOINT ["./bin/pawn-tour"]