package com.jansora.etcdui.controller;

import com.jansora.etcdui.model.Instance;
import com.jansora.etcdui.model.Node;
import com.jansora.etcdui.utils.Result;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * 〈一句话功能简述〉<br>
 * @file DataController.java
 * @description DataController
 *
 * @author Jansora
 * @date 2020-08-14 12:45
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@RestController
@RequestMapping("data")
public class DataController extends BaseController {

    @GetMapping("find")
    public Result find(Node node) {
        return dataService.find(node);
    }

    @PostMapping("insert")
    public Result insert(Node node) {
        return dataService.insert(node);
    }

    @DeleteMapping("delete")
    public Result delete(Node node) {
        return dataService.delete(node);
    }
}