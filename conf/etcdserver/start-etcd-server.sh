mkdir -p $APP/data/logs/

nohup /usr/bin/etcd $APP/conf/etcdserver/server.yml >> $APP/data/logs/server.log &

nohup /usr/bin/etcd $APP/conf/etcdserver/server1.yml >> $APP/data/logs/server1.log &
nohup /usr/bin/etcd $APP/conf/etcdserver/server2.yml >> $APP/data/logs/server2.log &
nohup /usr/bin/etcd $APP/conf/etcdserver/server3.yml >> $APP/data/logs/server3.log &
nohup /usr/bin/etcd $APP/conf/etcdserver/server4.yml >> $APP/data/logs/server4.log &
nohup /usr/bin/etcd $APP/conf/etcdserver/server5.yml >> $APP/data/logs/server5.log &