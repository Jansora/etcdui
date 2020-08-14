package com.jansora.etcdui.client;

import com.google.common.annotations.Beta;
import com.jansora.etcdui.model.KVData;
import com.jansora.etcdui.utils.ConstantUtils;
import com.jansora.etcdui.utils.Result;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.options.GetOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

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
@Repository
public class AdminClient extends EtcdClient {
    private static final Logger LOGGER = LoggerFactory.getLogger(AdminClient.class);

    public AdminClient() {
        super("http://10.243.147.87:2379");
    }

    @Override
    public Result get(String prefix, String key, GetOption getOption, Class clazz) {
        checkNotNull(key, "key should not be null");

        try {
            GetResponse response = this.kv.get(BYTE(formatKey(prefix, key)), getOption).get();
            List<KVData> data =
                    response.getKvs().stream()
                            .filter(kv_ -> kv_.getKey().startsWith(BYTE(ConstantUtils.APP)))
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


}