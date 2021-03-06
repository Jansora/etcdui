FROM ubuntu:20.04

ENV APP /app
ENV version 1.0.0

RUN apt update && apt install ca-certificates -y

COPY ./sources.list /etc/apt/sources.list

RUN cat /etc/apt/sources.list
RUN rm -rf /var/lib/apt/lists/*

RUN apt-get update

RUN apt-get install nginx etcd openjdk-8-jdk -y


RUN mkdir -p ${APP}
RUN mkdir -p ${APP}/logs

COPY ./backend/target/etcdui-${version}.jar /app/server.jar

COPY ./nginx.conf /etc/nginx/nginx.conf

COPY ./conf /app/conf

WORKDIR ${APP}

CMD ["sh","-c", "bash $APP/conf/etcdserver/start-etcd-server.sh && service nginx restart && java -jar server.jar"]