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

public class Menu implements Serializable {

    // 父菜单
    private AdminMenu adminMenu;

    // 子菜单
    private List<AdminMenu> childMenuList;

    public AdminMenu getAdminMenu() {
        return adminMenu;
    }

    public void setAdminMenu(AdminMenu adminMenu) {
        this.adminMenu = adminMenu;
    }

    public List<AdminMenu> getChildMenuList() {
        return childMenuList;
    }

    public void setChildMenuList(List<AdminMenu> childMenuList) {
        this.childMenuList = childMenuList;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "adminMenu=" + adminMenu +
                ", childMenuList=" + childMenuList +
                '}';
    }
}
