sudo docker run -d -p 9090:9003 --name etcdui \
-v /root/docker/etcdui/data:/app/data \
-v /root/g/etcdui/frontend/build:/app/dist jansora/etcdui:v1 \
