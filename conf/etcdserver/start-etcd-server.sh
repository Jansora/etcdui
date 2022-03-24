mkdir -p /root/data/logs/

nohup /usr/bin/etcd --config-file /root/conf/etcdserver/server.yml >> /root/data/logs/server.log &
