sudo docker run -d -p 9090:9003 -p 1111:1111 -p 1112:2222 -p 1113:3333 -p 1114:4444 -p 1115:5555 --name etcdui --restart always \
-v /root/docker/etcdui/data:/app/data \
-v /root/g/etcdui/frontend/build:/app/dist jansora/etcdui:v1 \
