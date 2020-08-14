package com.jansora.etcdui.service;

import com.jansora.etcdui.client.EtcdClient;
import com.jansora.etcdui.model.Instance;
import com.jansora.etcdui.model.Node;
import com.jansora.etcdui.utils.ConstantUtils;
import com.jansora.etcdui.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/*
 * 〈一句话功能简述〉<br>
 * @file InstanceService.java
 * @description InstanceService
 *
 * @author Jansora
 * @date 2020-08-13 16:08
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class DataService extends BaseService {


    public Result find(Node node) {
        if(StringUtils.isEmpty(node.getKey())) return FAILED("key is null");
        EtcdClient client = pool.getClient(node.getUri());
        return client.get(null, node.getKey(),
                ListDirOption(node.getKey()), String.class
        );
    }
    public Result insert(Node node) {

        EtcdClient client = pool.getClient(node.getUri());

        return client.putAndGet(null,
                node.getKey(), node.getValue(), GetFirstOption(), String.class);

    }

    public Result delete(Node node) {
        EtcdClient client = pool.getClient(node.getUri());

        return client.delete(null, node.getUri());
    }
}