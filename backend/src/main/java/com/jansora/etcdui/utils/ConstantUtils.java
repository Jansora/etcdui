package com.jansora.etcdui.utils;

import io.etcd.jetcd.ByteSequence;

import static com.google.common.base.Charsets.UTF_8;

/*
 * 〈一句话功能简述〉<br>
 * @file ConstantUtils.java
 * @description ConstantUtils
 *
 * @author Jansora
 * @date 2020-08-13 16:00
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class ConstantUtils {
    /*
       数据挂载根目录
    */
    public static final String APP = "/ETCDUI";

    /*
       Client TEST
    */
    public static final String CLIENT_TEST = APP + "/CLIENT_TEST";

    /*
       客户端 Etcd 实例列表
    */
    public static final  String ETCD_INSTANCE_LIST = APP + "/INSTANCE_LIST";


    /*
       客户端 Etcd Value默认值, 有的时候我们只关注key, 不关注value;
    */
    public static final  String ETCD_DEFAULT_VALUE = "";


}