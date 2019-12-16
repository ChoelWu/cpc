package com.joe.commons.app;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/7/3
// +----------------------------------------------------------------------
// | Author: joe
// +----------------------------------------------------------------------
// | Description: 响应码枚举类
// +----------------------------------------------------------------------

public enum AppResponseStatus {
    SUCCESS(1, "SUCCESS"),
    FAIL(0, "FAIL"),
    ERROR(-1, "ERROR");

    private final int status;
    private final String statusDesc;

    AppResponseStatus(int status, String statusDesc) {
        this.status = status;
        this.statusDesc = statusDesc;
    }

    public int getStatus() {
        return status;
    }

    public String getStatusDesc() {
        return statusDesc;
    }
}
