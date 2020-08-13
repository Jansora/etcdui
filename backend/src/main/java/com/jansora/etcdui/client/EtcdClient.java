package com.jansora.etcdui.client;

import com.google.common.annotations.Beta;
import com.google.gson.Gson;
import com.jansora.etcdui.utils.ConstantUtils;
import com.jansora.etcdui.utils.Result;
import com.jansora.etcdui.utils.BaseUtils;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.options.GetOption;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.base.Preconditions.checkNotNull;

/*
 * 〈一句话功能简述〉<br>
 * @file Client.java
 * @description Client
 *
 * @author Jansora
 * @date 2020-08-13 10:24
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Beta
public class EtcdClient {

    private Client client;

    private KV kv;

    private String endpoint;

    private static final Gson gson = new Gson();


    @Data
    private class KVData implements Serializable {
        private String key;
        private Object value;
        private long version;

        private long lease;
        private long createRevision;
        private long modRevision;
        public KVData(KeyValue data, Class clazz) {
            if (data == null) return;
            this.key = data.getKey().toString(UTF_8);
            this.value = gson.fromJson(data.getValue().toString(UTF_8), clazz);
            this.version = data.getVersion();
            this.lease = data.getLease();
            this.createRevision = data.getCreateRevision();
            this.modRevision = data.getModRevision();
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(EtcdClient.class);

    public EtcdClient(String endpoint) {
        this.endpoint = endpoint;
        this.client = Client.builder().endpoints(endpoint).build();
        this.kv = this.client.getKVClient();
    }

    public Result put(String key) {
        checkNotNull(key, "key should not be null");
        String value = ConstantUtils.ETCD_DEFAULT_VALUE;

        return this.put(key, value);

    }
    public Result put(String key, Object value) {
        checkNotNull(key, "key should not be null");
        checkNotNull(value, "option should not be null");
        try {
            ByteSequence key_ = ByteSequence.from(key.getBytes());
            ByteSequence value_ = ByteSequence.from(gson.toJson(value).getBytes());
            this.kv.put(key_, value_).get();
            return BaseUtils.SUCCESSFUL();
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.error("EtcdClient put exec failed! key: {}, {}", key, e);
            return BaseUtils.FAILED("EtcdClient put exec failed!");
        }

    }
    public Result get(String key, GetOption getOption, Class clazz) {
        checkNotNull(key, "key should not be null");

        try {
            ByteSequence key_ = ByteSequence.from(key.getBytes());
            GetResponse response = this.kv.get(key_, getOption).get();
            List<KVData> data =
                    response.getKvs().stream().map(kv -> new KVData(kv, clazz)).collect(Collectors.toList());
            return BaseUtils.SUCCESSFUL(data, (long) data.size());
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.error("EtcdClient get exec failed! key: {}, {}", key, e);
            return BaseUtils.FAILED("EtcdClient get exec failed!:");
        }

    }
    public Result get(String key) {
        GetOption getOption = GetOption.newBuilder().build();
        Class clazz = String.class;
        return this.get(key, getOption, clazz);
    }
    public Result get(String key, Class clazz) {
        GetOption getOption = GetOption.newBuilder().build();
        return this.get(key, getOption, clazz);
    }

    public Result putAndGet(String key) {
        String value = ConstantUtils.ETCD_DEFAULT_VALUE;
        Result result = this.put(key, value);
        if(result.getStatus()) {
            return this.get(key);
        }
        return result;
    }
    public Result putAndGet(String key, Object value) {
        Result result = this.put(key, value);
        if(result.getStatus()) {
            return this.get(key);
        }
        return result;
    }

    public Result delete(String key) {
        checkNotNull(key, "key should not be null");

        try {
            ByteSequence key_ = ByteSequence.from(key.getBytes());
            this.kv.delete(key_).get();
            return BaseUtils.SUCCESSFUL();
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.error("EtcdClient delete exec failed! key: {}, {}", key, e);
            return BaseUtils.FAILED("EtcdClient get exec failed!:");
        }

    }

}