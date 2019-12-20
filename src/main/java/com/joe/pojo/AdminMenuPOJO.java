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

import com.joe.entity.AdminAuth;
import com.joe.entity.AdminMenu;

import java.io.Serializable;
import java.util.List;

public class AdminMenuPOJO implements Serializable {

    // 菜单
    private AdminMenu menu;

    // 权限列表
    private List<AdminAuth> adminAuthList;

    public AdminMenu getMenu() {
        return menu;
    }

    public void setMenu(AdminMenu menu) {
        this.menu = menu;
    }

    public List<AdminAuth> getAdminAuthList() {
        return adminAuthList;
    }

    public void setAdminAuthList(List<AdminAuth> adminAuthList) {
        this.adminAuthList = adminAuthList;
    }

    @Override
    public String toString() {
        return "AdminMenuPOJO{" +
                "menu=" + menu +
                ", adminAuthList=" + adminAuthList +
                '}';
    }
}
