package com.jansora.etcdui.client;

import com.google.common.annotations.Beta;
import com.google.gson.Gson;
import com.jansora.etcdui.model.KVData;
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
import org.springframework.util.StringUtils;

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
public class EtcdClient extends BaseUtils {

    protected Client client;

    protected KV kv;

    protected String endpoint;

    protected KVData kvData;

    private static final Gson gson = new Gson();

    private static final Logger LOGGER = LoggerFactory.getLogger(EtcdClient.class);

    public EtcdClient(String endpoint) {
        this.endpoint = endpoint;
        this.client = Client.builder().endpoints(endpoint).build();
        this.kv = this.client.getKVClient();
    }

    public Result put(String prefix, String key, Object value) {
        checkNotNull(key, "key should not be null");
        checkNotNull(value, "option should not be null");
        try {
            this.kv.put(BYTE(formatKey(prefix, key)), BYTE(gson.toJson(value))).get();
            return SUCCESSFUL();
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.error("EtcdClient put exec failed! key: {}, {}", key, e);
            return FAILED("EtcdClient put exec failed!");
        }

    }
    public Result get(String prefix, String key, GetOption getOption, Class clazz) {
        checkNotNull(key, "key should not be null");

        try {
            GetResponse response = this.kv.get(BYTE(formatKey(prefix, key)), getOption).get();
            List<KVData> data =
                    response.getKvs().stream()
                            .filter(kv_ -> !kv_.getKey().startsWith(BYTE(ConstantUtils.APP)))
                            .peek(System.out::println)
                            .map(kv_ -> new KVData(kv_, clazz))
                            .map(kv_ -> {
                                kv_.setKey(kv_.getKey().replaceFirst(prefix + "/", ""));
                                return kv_;
                            })
                            .collect(Collectors.toList());

            return SUCCESSFUL(data, (long) data.size());
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.error("EtcdClient get exec failed! key: {}, {}", key, e);
            return FAILED("EtcdClient get exec failed!:");
        }

    }


    public Result putAndGet(String prefix, String key, Object value, GetOption getOption, Class clazz) {
        Result result = this.put(prefix, key, value);
        if(result.getStatus()) {
            return this.get(prefix, key, getOption, clazz);
        }
        return result;
    }

    public Result delete(String prefix,String key) {
        checkNotNull(key, "key should not be null");

        try {
            ByteSequence key_ = ByteSequence.from(formatKey(prefix, key).getBytes());
            this.kv.delete(key_).get();
            return SUCCESSFUL();
        } catch (ExecutionException | InterruptedException e) {
            LOGGER.error("EtcdClient delete exec failed! key: {}, {}", key, e);
            return FAILED("EtcdClient get exec failed!:");
        }

    }

    protected String formatKey(String prefix,String key) {

        if(!StringUtils.isEmpty(prefix) && !StringUtils.isEmpty(key)) {
            return prefix + "/" + key;
        } else {
            if(!StringUtils.isEmpty(prefix)) {
                return prefix;
            }
            if(!StringUtils.isEmpty(key)) {
                return key;
            }
        }
        return key;
    }

}