package com.jansora.etcdui.client;

import com.jansora.etcdui.utils.BaseUtils;
import com.jansora.etcdui.utils.ConstantUtils;
import com.jansora.etcdui.utils.Result;
import io.etcd.jetcd.Client;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import static com.google.common.base.Preconditions.checkNotNull;

/*
 * 〈一句话功能简述〉<br>
 * @file EtcdConnectPool.java
 * @description EtcdConnectPool
 *
 * @author jansora
 * @date 2020-08-13 11:03
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Repository
public class EtcdConnectPool extends BaseUtils {

    @Data
    @ToString
    private class Node {

        EtcdClient etcdClient;

        String endpoint;
        Long updateAt;
        Node() {
            this.endpoint = null;
            this.etcdClient = null;
            this.updateAt = System.currentTimeMillis();
        }
        Node(String endpoint) {
            this.updateAt = System.currentTimeMillis();
            this.etcdClient = new EtcdClient(endpoint);
        }

    }


    private Integer size = 10;



    private final ConcurrentHashMap<String, Node> data = new ConcurrentHashMap<>();

    public EtcdConnectPool() { }
    public EtcdConnectPool(Integer size) {
        if(size > 0) this.size = size;
    }


    private void updateClient(String endpoint) {
        Optional<Map.Entry<String, Node>>
                lru = this.data.entrySet().stream().max(
                        Comparator.comparing(e -> e.getValue().getUpdateAt()));

        lru.ifPresent(l -> this.data.remove(l.getKey()));
        this.data.put(endpoint, new Node(endpoint));

    }

    public synchronized EtcdClient getClient(String endpoint) {
        EtcdClient client = this.get(endpoint);
        return client != null ? client : this.putAndGet(endpoint);
    }

    public EtcdClient get(String endpoint) {
        Node node;
        if((node = this.data.get(endpoint)) != null) {
            node.setUpdateAt(System.currentTimeMillis());
            return node.getEtcdClient();
        }
        return null;
    }
    public void put(String endpoint) {
        checkNotNull(endpoint, "key should not be null");

        if(this.data.size() < this.size) {
            size++;
            this.data.put(endpoint, new Node(endpoint));
        } else {
            this.updateClient(endpoint);
        }
    }
    public EtcdClient putAndGet(String endpoint) {
        this.put(endpoint);
        return this.get(endpoint);
    }

    public Result validClient(String endpoint) {
        EtcdClient client = this.putAndGet(endpoint);
        Result status = client.putAndGet(ConstantUtils.CLIENT_TEST,
                "", ConstantUtils.ETCD_DEFAULT_VALUE, GetFirstOption(), String.class);
        if(!status.getStatus()) return status;
        return client.delete(ConstantUtils.CLIENT_TEST, "");

    }
}