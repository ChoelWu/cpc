package com.joe.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author joe
 * @since 2019-11-17
 */
@TableName("cpc_admin_menu")
public class AdminMenu implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 菜单ID
     */
    @TableId(value = "menu_id", type = IdType.AUTO)
    private Long menuId;

    /**
     * 菜单编号 AMNO+yyyymmddhhmmss+1位随机大写字母+5位随机数字
     */
    private String menuNo;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单级别 1-父级菜单 2-子级菜单
     */
    private String menuLevel;

    /**
     * 菜单链接地址
     */
    private String menuUrl;

    /**
     * 菜单编号
     */
    private String menuIndex;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 父级菜单
     */
    private String parentMenuNo;

    /**
     * 菜单状态 0-禁用 1-启用
     */
    private String menuStatus;

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


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
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

    public String getMenuLevel() {
        return menuLevel;
    }

    public void setMenuLevel(String menuLevel) {
        this.menuLevel = menuLevel;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuIndex() {
        return menuIndex;
    }

    public void setMenuIndex(String menuIndex) {
        this.menuIndex = menuIndex;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getParentMenuNo() {
        return parentMenuNo;
    }

    public void setParentMenuNo(String parentMenuNo) {
        this.parentMenuNo = parentMenuNo;
    }

    public String getMenuStatus() {
        return menuStatus;
    }

    public void setMenuStatus(String menuStatus) {
        this.menuStatus = menuStatus;
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
        return "AdminMenu{" +
        "menuId=" + menuId +
        ", menuNo=" + menuNo +
        ", menuName=" + menuName +
        ", menuLevel=" + menuLevel +
        ", menuUrl=" + menuUrl +
        ", menuIndex=" + menuIndex +
        ", menuIcon=" + menuIcon +
        ", parentMenuNo=" + parentMenuNo +
        ", menuStatus=" + menuStatus +
        ", bak=" + bak +
        ", addTime=" + addTime +
        ", addUserNo=" + addUserNo +
        ", updateTime=" + updateTime +
        ", updateUserNo=" + updateUserNo +
        "}";
    }
}
