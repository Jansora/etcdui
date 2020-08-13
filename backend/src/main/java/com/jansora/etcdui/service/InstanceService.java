package com.jansora.etcdui.service;

import com.jansora.etcdui.client.EtcdClient;
import com.jansora.etcdui.model.Instance;
import com.jansora.etcdui.utils.ConstantUtils;
import com.jansora.etcdui.utils.Result;
import org.springframework.stereotype.Service;

/*
 * 〈一句话功能简述〉<br>
 * @file CommonService.java
 * @description CommonService
 *
 * @author Jansora
 * @date 2020-08-13 16:08
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class InstanceService extends BaseService {


    public Result findAll() {

        EtcdClient adminClient = pool.getAdminClient();

        return adminClient.get(
                ConstantUtils.ETCD_INSTANCE_LIST,
                ListDirOption(ConstantUtils.ETCD_INSTANCE_LIST), Instance.class
        );
    }

    public Result insert(Instance instance) {

        if(pool.validClient(instance.getUri())) {
            instance.setHash(instance.getUri().hashCode());
            String key = ConstantUtils.ETCD_INSTANCE_LIST + "/" + instance.getHash().toString();
            return adminClient.putAndGet(key, instance);
        }
        return FAILED("客户端校验失败");
    }

    public Result delete(String hash) {
        EtcdClient adminClient = pool.getAdminClient();

        return adminClient.delete(ConstantUtils.ETCD_INSTANCE_LIST + "/" + hash);
    }
}