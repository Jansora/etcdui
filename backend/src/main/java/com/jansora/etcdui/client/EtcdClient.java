package com.jansora.etcdui.client;

import com.google.common.annotations.Beta;
import com.jansora.etcdui.utils.ConstantUtils;
import com.jansora.etcdui.utils.Result;
import com.jansora.etcdui.utils.ResultUtils;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.options.GetOption;
import jdk.vm.ci.meta.Constant;
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




    @Data
    private class KVData implements Serializable {
        private String key;
        private String value;
        private long version;

        private long lease;
        private long createRevision;
        private long modRevision;
        public KVData(KeyValue data) {
            if (data == null) return;
            this.key = data.getKey().toString(UTF_8);
            this.value = data.getValue().toString(UTF_8);
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
    public Result put(String key, String value) {
        checkNotNull(key, "key should not be null");
        checkNotNull(value, "option should not be null");

        try {
            ByteSequence key_ = ByteSequence.from(key.getBytes());
            ByteSequence value_ = ByteSequence.from(value.getBytes());
            this.kv.put(key_, value_).get();
            return ResultUtils.SUCCESSFUL();
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.error("EtcdClient put exec failed! key: {}, {}", key, e);
            return ResultUtils.FAILED("EtcdClient put exec failed!");
        }

    }
    public Result get(String key, GetOption getOption) {
        checkNotNull(key, "key should not be null");

        try {
            ByteSequence key_ = ByteSequence.from(key.getBytes());
            GetResponse response = this.kv.get(key_, getOption).get();
            List<KVData> data =
                    response.getKvs().stream().map(KVData::new).collect(Collectors.toList());
            return ResultUtils.SUCCESSFUL(data, (long) data.size());
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.error("EtcdClient get exec failed! key: {}, {}", key, e);
            return ResultUtils.FAILED("EtcdClient get exec failed!:");
        }

    }
    public Result get(String key) {
        GetOption getOption = GetOption.newBuilder().build();
        return this.get(key, getOption);
    }

    public Result putAndGet(String key) {
        String value = ConstantUtils.ETCD_DEFAULT_VALUE;
        Result result = this.put(key, value);
        if(result.getStatus()) {
            return this.get(key);
        }
        return result;
    }
    public Result putAndGet(String key, String value) {
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
            return ResultUtils.SUCCESSFUL();
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.error("EtcdClient delete exec failed! key: {}, {}", key, e);
            return ResultUtils.FAILED("EtcdClient get exec failed!:");
        }

    }

}