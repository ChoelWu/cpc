package com.joe.commons.app;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/7/25
// +----------------------------------------------------------------------
// | Author: joe
// +----------------------------------------------------------------------
// | Description: 验证码操作类型定义
// +----------------------------------------------------------------------

public enum VerifyAction {
    EMAIL_REGISTER("01", "EMAIL_REGISTER_VERIFY_CODE"),
    MOBILE_REGISTER("02", "MOBILE_REGISTER_VERIFY_CODE"),
    EMAIL_LOGIN("11", "EMAIL_LOGIN_VERIFY_CODE"),
    MOBILE_LOGIN("12", "MOBILE_LOGIN_VERIFY_CODE"),
    EMAIL_ALTER_PASSWORD("21", "EMAIL_ALTER_PASSWORD_VERIFY_CODE"),
    MOBILE_ALTER_PASSWORD("22", "MOBILE_ALTER_PASSWORD_VERIFY_CODE");

    private String actionCode;
    private String actionDesc;

    VerifyAction(String actionCode, String actionDesc) {
        this.actionCode = actionCode;
        this.actionDesc = actionDesc;
    }

    public String getActionCode() {
        return actionCode;
    }

    public String getActionDesc() {
        return actionDesc;
    }
}
