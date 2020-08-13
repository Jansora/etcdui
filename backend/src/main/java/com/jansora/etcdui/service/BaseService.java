package com.jansora.etcdui.service;

import com.jansora.etcdui.client.EtcdClient;
import com.jansora.etcdui.client.EtcdConnectPool;
import com.jansora.etcdui.utils.ResultUtils;
import io.etcd.jetcd.ByteSequence;
import io.etcd.jetcd.options.GetOption;
import org.springframework.stereotype.Service;
import static com.google.common.base.Charsets.UTF_8;

import javax.annotation.Resource;

/*
 * 〈一句话功能简述〉<br>
 * @file CommonService.java
 * @description CommonService
 *
 * @author Jansora
 * @date 2020-08-13 13:20
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class BaseService extends ResultUtils {

    @Resource(type = EtcdConnectPool.class)
    protected EtcdConnectPool pool;

    protected EtcdClient adminClient = pool.getAdminClient();

    protected GetOption ListDirOption(String key) {
        ByteSequence key_ = ByteSequence.from(key, UTF_8);
        GetOption getOption = GetOption.newBuilder().withPrefix(BYTE(key)).build();
        return getOption;
    }

    protected static ByteSequence BYTE(String key) {
        return ByteSequence.from(key, UTF_8);
    }
}