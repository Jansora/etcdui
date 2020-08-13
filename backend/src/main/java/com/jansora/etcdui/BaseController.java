package com.jansora.etcdui;

import com.jansora.etcdui.client.EtcdConnectPool;
import com.jansora.etcdui.service.BaseService;
import com.jansora.etcdui.utils.Result;
import com.jansora.etcdui.utils.ResultUtils;
import org.springframework.web.bind.annotation.GetMapping;
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
public class BaseController extends ResultUtils {
    @Resource(type = EtcdConnectPool.class)
    EtcdConnectPool pool;

    @Resource(type = BaseService.class)
    BaseService baseService;

    @GetMapping("helloworld")
    public Result hello() {
        String hash = Integer.toHexString(pool.hashCode());
        return baseService.hello();
    }
}