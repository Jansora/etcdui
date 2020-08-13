package com.jansora.etcdui.controller;

import com.jansora.etcdui.model.Instance;
import com.jansora.etcdui.utils.Result;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 〈一句话功能简述〉<br>
 * @file InstanceController.java
 * @description 管理Etcd实例列表
 *
 * @author Jansora
 * @date 2020-08-13 13:17
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping("instance")
public class InstanceController extends BaseController {


    @GetMapping("findAll")
    public Result findAll() {
        return instanceService.findAll();
    }

    @PostMapping("insert")
    public Result insert(Instance instance) {
        return instanceService.insert(instance);
    }

    @DeleteMapping("delete/{hash}")
    public Result delete(@PathVariable String hash) {
        return instanceService.delete(hash);
    }

}