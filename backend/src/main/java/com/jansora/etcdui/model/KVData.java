package com.jansora.etcdui.model;

import com.google.gson.Gson;
import io.etcd.jetcd.KeyValue;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

import static com.google.common.base.Charsets.UTF_8;

/*
 * 〈一句话功能简述〉<br>
 * @file KVData.java
 * @description KVData
 *
 * @author Jansora
 * @date 2020-08-14 12:40
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Data
@ToString
public class KVData implements Serializable {
    private String key;
    private Object value;
    private long version;

    private long lease;
    private long createRevision;
    private long modRevision;
    private static final Gson gson = new Gson();

    public KVData(KeyValue data, Class clazz) {
        if (data == null) return;
        this.key = data.getKey().toString(UTF_8);
        this.value = gson.fromJson(data.getValue().toString(UTF_8), clazz);
//        try {
//            this.value = gson.fromJson(data.getValue().toString(UTF_8), clazz);
//        } catch (Exception e) {
//            this.value = data.getValue().toString(UTF_8);
//        }
        this.version = data.getVersion();
        this.lease = data.getLease();
        this.createRevision = data.getCreateRevision();
        this.modRevision = data.getModRevision();
    }
}