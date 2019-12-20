package com.joe.pojo;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/13
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 
// +----------------------------------------------------------------------

import com.joe.entity.AdminMenu;

import java.io.Serializable;
import java.util.List;

public class AuthPOJO implements Serializable {

    // 父菜单
    private AdminMenu parentMenu;

    // 子菜单列表
    private List<AdminMenuPOJO> childMenuPOJOList;

    public AdminMenu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(AdminMenu parentMenu) {
        this.parentMenu = parentMenu;
    }

    public List<AdminMenuPOJO> getChildMenuPOJOList() {
        return childMenuPOJOList;
    }

    public void setChildMenuPOJOList(List<AdminMenuPOJO> childMenuPOJOList) {
        this.childMenuPOJOList = childMenuPOJOList;
    }

    @Override
    public String toString() {
        return "AuthPOJO{" +
                "parentMenu=" + parentMenu +
                ", childMenuPOJOList=" + childMenuPOJOList +
                '}';
    }
}
