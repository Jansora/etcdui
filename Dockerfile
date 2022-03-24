FROM ubuntu:20.04


ENV version 1.0.0

RUN apt update && apt install ca-certificates -y

COPY ./sources.list /etc/apt/sources.list

RUN cat /etc/apt/sources.list
RUN rm -rf /var/lib/apt/lists/*

RUN apt-get update

RUN apt-get install nginx etcd openjdk-8-jdk -y


COPY ./frontend/build /dist

COPY ./backend/target/etcdui-${version}.jar /root/server.jar

COPY ./nginx.conf /etc/nginx/nginx.conf

COPY ./conf /root/conf

WORKDIR /root

CMD ["sh","-c", "bash /root/conf/etcdserver/start-etcd-server.sh && service nginx restart && java -jar server.jar"]