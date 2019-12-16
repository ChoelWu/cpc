package com.joe.pojo;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/21
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 
// +----------------------------------------------------------------------

import java.util.Date;

public class Auth {
    /**
     * 权限ID
     */
    private Long authId;

    /**
     * 权限编号 AANO+yyyymmddhhmmss+1位随机大写字母+5位随机数字
     */
    private String authNo;

    /**
     * 权限名称
     */
    private String authName;

    /**
     * 权限 shiro
     */
    private String authPermission;

    /**
     * 所属菜单
     */
    private String menuNo;

    /**
     * 所属菜单名称
     */
    private String menuName;

    /**
     * 备用字段
     */
    private String bak;

    /**
     * 添加时间
     */
    private Date addTime;

    /**
     * 添加人
     */
    private String addUserNo;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateUserNo;

    public Long getAuthId() {
        return authId;
    }

    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    public String getAuthNo() {
        return authNo;
    }

    public void setAuthNo(String authNo) {
        this.authNo = authNo;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthPermission() {
        return authPermission;
    }

    public void setAuthPermission(String authPermission) {
        this.authPermission = authPermission;
    }

    public String getMenuNo() {
        return menuNo;
    }

    public void setMenuNo(String menuNo) {
        this.menuNo = menuNo;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getAddUserNo() {
        return addUserNo;
    }

    public void setAddUserNo(String addUserNo) {
        this.addUserNo = addUserNo;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserNo() {
        return updateUserNo;
    }

    public void setUpdateUserNo(String updateUserNo) {
        this.updateUserNo = updateUserNo;
    }

    @Override
    public String toString() {
        return "Auth{" +
                "authId=" + authId +
                ", authNo='" + authNo + '\'' +
                ", authName='" + authName + '\'' +
                ", authPermission='" + authPermission + '\'' +
                ", menuNo='" + menuNo + '\'' +
                ", menuName='" + menuName + '\'' +
                ", bak='" + bak + '\'' +
                ", addTime=" + addTime +
                ", addUserNo='" + addUserNo + '\'' +
                ", updateTime=" + updateTime +
                ", updateUserNo='" + updateUserNo + '\'' +
                '}';
    }
}
