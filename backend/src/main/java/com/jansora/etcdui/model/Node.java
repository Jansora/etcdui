package com.jansora.etcdui.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/*
 * 〈一句话功能简述〉<br>
 * @file Node.java
 * @description Node
 *
 * @author Jansora
 * @date 2020-08-13 19:39
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Data
@ToString
public class Node implements Serializable {
    private String uri;
    private String key;
    private String value;

}