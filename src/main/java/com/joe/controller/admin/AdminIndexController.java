package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/11
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 管理端首页
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.joe.entity.AdminMenu;
import com.joe.pojo.Menu;
import com.joe.service.system.AdminMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/admin/index")
public class AdminIndexController {
    @Resource
    private AdminMenuService adminMenuService;

    @RequestMapping("index.do")
    public String index(Model model) {
        // 查询出所有的菜单
        QueryWrapper<AdminMenu> adminMenuQueryWrapper = new QueryWrapper<>();
        adminMenuQueryWrapper.orderByAsc("menu_level", "menu_index");
        List<AdminMenu> adminMenuList = adminMenuService.list(adminMenuQueryWrapper);

        // 创建前台输出的菜单列表
        List<Menu> menuList = Lists.newArrayList();

        // 循环遍历菜单，生成前台需要的格式
        for (AdminMenu adminMenu : adminMenuList) {
            if (StringUtils.equals("1", adminMenu.getMenuLevel())) {
                // 创建菜单对象
                Menu menu = new Menu();
                // 子菜单列表
                List<AdminMenu> adminChildMenuList = Lists.newArrayList();

                // 循环组织子菜单
                for (AdminMenu adminChildMenu : adminMenuList) {
                    if (StringUtils.equals(adminMenu.getMenuNo(), adminChildMenu.getParentMenuNo())) {
                        adminChildMenuList.add(adminChildMenu);
                    }
                }

                // 存储父菜单
                menu.setAdminMenu(adminMenu);
                // 存储子菜单
                menu.setChildMenuList(adminChildMenuList);

                // 存储菜单
                menuList.add(menu);
            }
        }

        // 绑定菜单数据到前台
        model.addAttribute("menuList", menuList);
        return "Admin/Index/index";
    }

    @RequestMapping("home.do")
    public String Home() {
        return "Admin/Index/home";
    }
}
