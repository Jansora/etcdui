sudo docker run -d -p 9090:9003 --name etcdui \

-v ./frontend/build:/app/dist jansora/application:v2 \
