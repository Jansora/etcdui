package com.jansora.etcdui.controller;

import com.jansora.etcdui.client.AdminClient;
import com.jansora.etcdui.client.EtcdConnectPool;
import com.jansora.etcdui.service.BaseService;
import com.jansora.etcdui.service.InstanceService;
import com.jansora.etcdui.utils.BaseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/*
 * 〈一句话功能简述〉<br>
 * @file BaseController.java
 * @description BaseController
 *
 * @author Jansora
 * @date 2020-08-13 11:46
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping("/")
public class BaseController extends BaseUtils {

    @Resource(type = EtcdConnectPool.class)
    EtcdConnectPool pool;


    @Resource(type = BaseService.class)
    BaseService baseService;

    @Resource(type = InstanceService.class)
    InstanceService instanceService;

}