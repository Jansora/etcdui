package com.jansora.etcdui.client;

import com.google.common.annotations.Beta;
import com.google.gson.Gson;
import com.jansora.etcdui.utils.BaseUtils;
import com.jansora.etcdui.utils.ConstantUtils;
import com.jansora.etcdui.utils.Result;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.Client;
import io.etcd.jetcd.KV;
import io.etcd.jetcd.KeyValue;
import io.etcd.jetcd.kv.GetResponse;
import io.etcd.jetcd.options.GetOption;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

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
@Repository
public class AdminClient extends EtcdClient {

    public AdminClient() {
        super("http://10.243.147.87:2379");
    }

}