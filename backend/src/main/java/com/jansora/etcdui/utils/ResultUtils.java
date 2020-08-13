package com.jansora.etcdui.utils;


public class ResultUtils {

    public static Result FormatResult(boolean status, String message, Object data, Long total) {
        return Result.builder().status(status).message(message).data(data).total(total).build();
    }

    public static Result SUCCESSFUL() {
        return FormatResult(true, null, null, 0L);
    }
    public static Result SUCCESSFUL(Object data) {
        return FormatResult(true, null, data, 0L);
    }
    public static Result SUCCESSFUL(Object data, Long total) {
        return FormatResult(true, null, data, total);
    }
    public static Result FAILED() {
        return FormatResult(false, null, null, 0L);
    }
    public static Result FAILED(String message) {
        return FormatResult(false, message, null, 0L);
    }
    public static Result INVALID() {
        return FormatResult(false, "非法操作", null, 0L);
    }
    public static Result NOT_LOGIN() {
        return FormatResult(false, "未登录或者无权限操作", null, 0L);
    }

}
