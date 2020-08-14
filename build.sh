cd backend && mvn package -Dmaven.test.skip=true

cd ../ && docker build -t jansora/etcdui:v1 .
