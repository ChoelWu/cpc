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
import com.google.common.collect.Maps;
import com.joe.entity.AdminAuth;
import com.joe.entity.AdminMenu;
import com.joe.entity.AdminUser;
import com.joe.pojo.Menu;
import com.joe.service.system.AdminMenuService;
import com.joe.service.system.IndexArticleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/index")
public class AdminIndexController {
    @Resource
    private AdminMenuService adminMenuService;

    @Resource
    private IndexArticleService indexArticleService;

    /**
     * 父框架视图
     *
     * @param model   model
     * @param session session
     * @return 返回页面视图  --- 在循环菜单的时候同时查询权限列表，如果这个菜单（子菜单下面有权限，则允许显示，否则不显示）
     */
    @RequestMapping("index.do")
    public String index(Model model, HttpSession session) {
        // 根据session获取用户信息
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 查询出所有的权限
        List<AdminAuth> adminAuthList = (List<AdminAuth>) session.getAttribute("adminAuthList");
        List<String> permissionMenuNoList = Lists.newArrayList();
        for (AdminAuth adminAuth : adminAuthList) {
            permissionMenuNoList.add(adminAuth.getMenuNo());
        }

        String permissionMenuNoString = StringUtils.join(permissionMenuNoList, ",");
        permissionMenuNoString = "," + permissionMenuNoString + ",";

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
                    // 查看此菜单是否在授权的菜单里
//                    if (StringUtils.contains(permissionMenuNoString, "," + adminChildMenu.getMenuNo() + ",")) {
                        if (StringUtils.equals(adminMenu.getMenuNo(), adminChildMenu.getParentMenuNo())) {
                            adminChildMenuList.add(adminChildMenu);
                        }
//                    }
                }

                // 如果子菜单为空说明没有授权的子菜单，子菜单list不为空时才加入menu的list
                if (!adminChildMenuList.isEmpty()) {
                    // 存储父菜单
                    menu.setAdminMenu(adminMenu);
                    // 存储子菜单
                    menu.setChildMenuList(adminChildMenuList);
                    // 存储菜单
                    menuList.add(menu);
                }
            }
        }

        // 绑定菜单数据到前台
        model.addAttribute("menuList", menuList);
        model.addAttribute("adminUser", adminUser);

        // 返回页面视图
        return "Admin/Index/index";
    }

    @RequestMapping("home.do")
    public String Home(Model model) {
        // 查询文章总数
        Map<String, Object> conditions = Maps.newHashMap();
        conditions.put("1", "1");
        int articleNum = indexArticleService.countArticle(conditions);
        // 查询待审核文章数目
        conditions.put("articleStatus", "0");
        int auditNum = indexArticleService.countArticle(conditions);

        //
        Map<String, Object> broadList = Maps.newHashMap();
        broadList.put("articleNum", articleNum);
        broadList.put("auditNum", auditNum);

        model.addAttribute("broadList", broadList);

        return "Admin/Index/home";
    }
}
