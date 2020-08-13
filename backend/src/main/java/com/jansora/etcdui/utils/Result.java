package com.jansora.etcdui.utils;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/*
 * 〈一句话功能简述〉<br>
 * @file Result.java
 * @description Result
 *
 * @author Jansora
 * @date 2020-06-03 11:07
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */

@Data
@ToString
@Builder
public class Result implements Serializable {
    private long total;
    private Object data;
    private String message;
    private Boolean status = true;

    public final Result setSuccess(List<Object> data) {
        return Result.builder()
                .status(true)
                .data(data).build();
    }
    public final Result setSuccess(Object data) {
        return Result.builder()
                .status(true)
                .data(data).build();
    }

    public final Result setFailed (String message) {
        return Result.builder()
                .status(false)
                .data(data).build();
    }


}