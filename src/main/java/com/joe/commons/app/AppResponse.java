package com.joe.commons.app;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/7/3
// +----------------------------------------------------------------------
// | Author: joe
// +----------------------------------------------------------------------
// | Description: 全局响应类
// +----------------------------------------------------------------------

import com.fasterxml.jackson.annotation.JsonIgnore;

public class AppResponse<T> {
    private int status; // 响应状态
    private String message; // 响应信息
    private T data; // 响应数据

    private AppResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    private AppResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    /**
     * 判断响应是否成功
     *
     * @return 返回判断结果
     */
    @JsonIgnore
    public boolean isSuccess() {
        return this.status == AppResponseStatus.SUCCESS.getStatus();
    }

    public static <T> AppResponse<T> success() {
        return new AppResponse<>(AppResponseStatus.SUCCESS.getStatus(), AppResponseStatus.SUCCESS.getStatusDesc());
    }

    public static <T> AppResponse<T> success(String message) {
        return new AppResponse<>(AppResponseStatus.SUCCESS.getStatus(), message);
    }

    public static <T> AppResponse<T> success(T data) {
        return new AppResponse<>(AppResponseStatus.SUCCESS.getStatus(), AppResponseStatus.SUCCESS.getStatusDesc(), data);
    }

    public static <T> AppResponse<T> success(String message, T data) {
        return new AppResponse<>(AppResponseStatus.SUCCESS.getStatus(), message, data);
    }

    public static <T> AppResponse<T> fail() {
        return new AppResponse<>(AppResponseStatus.FAIL.getStatus(), AppResponseStatus.FAIL.getStatusDesc());
    }

    public static <T> AppResponse<T> fail(String message) {
        return new AppResponse<>(AppResponseStatus.FAIL.getStatus(), message);
    }

    public static <T> AppResponse<T> fail(T data) {
        return new AppResponse<>(AppResponseStatus.FAIL.getStatus(), AppResponseStatus.FAIL.getStatusDesc(), data);
    }

    public static <T> AppResponse<T> fail(String message, T data) {
        return new AppResponse<>(AppResponseStatus.FAIL.getStatus(), message, data);
    }

    public static <T> AppResponse<T> error() {
        return new AppResponse<>(AppResponseStatus.ERROR.getStatus(), AppResponseStatus.ERROR.getStatusDesc());
    }

    public static <T> AppResponse<T> error(String message) {
        return new AppResponse<>(AppResponseStatus.ERROR.getStatus(), message);
    }

    public static <T> AppResponse<T> error(T data) {
        return new AppResponse<>(AppResponseStatus.ERROR.getStatus(), AppResponseStatus.ERROR.getStatusDesc(), data);
    }

    public static <T> AppResponse<T> error(String message, T data) {
        return new AppResponse<>(AppResponseStatus.ERROR.getStatus(), message, data);
    }

    public static <T> AppResponse<T> Response(int status, String sattusDesc) {
        return new AppResponse<>(status, sattusDesc);
    }

    public static <T> AppResponse<T> Response(int status, String sattusDesc, T data) {
        return new AppResponse<>(status, sattusDesc, data);
    }
}
