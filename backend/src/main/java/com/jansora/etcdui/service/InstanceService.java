package com.jansora.etcdui.service;

import com.jansora.etcdui.client.EtcdClient;
import com.jansora.etcdui.model.Instance;
import com.jansora.etcdui.utils.ConstantUtils;
import com.jansora.etcdui.utils.Result;
import org.springframework.stereotype.Service;

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
public class InstanceService extends BaseService {


    public Result findAll() {

        return adminClient.get(
                ConstantUtils.ETCD_INSTANCE_LIST, "",
                ListDirOption(ConstantUtils.ETCD_INSTANCE_LIST), Instance.class
        );
    }

    public Result insert(Instance instance) {

        Result status = pool.validClient(instance.getUri());
        if(status.getStatus()) {
            return adminClient.putAndGet(ConstantUtils.ETCD_INSTANCE_LIST, instance.getUri(), instance, GetFirstOption(), Instance.class);
        }
        return FAILED("该实例无法连接, 请重试");
    }

    public Result delete(String uri) {

        return adminClient.delete(ConstantUtils.ETCD_INSTANCE_LIST, uri);
    }
}