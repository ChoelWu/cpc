package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/13
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 管理端菜单
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.AdminDict;
import com.joe.entity.AdminUser;
import com.joe.pojo.Menu;
import com.joe.service.system.AdminDictService;
import com.joe.service.system.AdminMenuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.joe.entity.AdminMenu;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/menu")
public class AdminMenuController {
    private Logger logger = LoggerFactory.getLogger(AdminMenuController.class);

    @Resource
    private AdminMenuService adminMenuService;

    @Resource
    private AdminDictService dictService;

    /**
     * 菜单列表显示
     *
     * @param model model
     * @return 返回页面
     */
    @RequestMapping("view.do")
    public String view(Model model) {
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

        // 返回页面
        return "Admin/Menu/index";
    }

    /**
     * 新增页面显示
     *
     * @return 返回新增页面
     */
    @RequestMapping("add_page.do")
    public String addPage(Model model) {
        // 菜单等级
        List<AdminDict> menuLevelDictList = dictService.getDictListByDictName("menuLevel");
        // 菜单状态
        List<AdminDict> menuStatusDictList = dictService.getDictListByDictName("menuStatus");

        // 所有启用的父级菜单列表
        QueryWrapper<AdminMenu> adminMenuQueryWrapper = new QueryWrapper<>();
        adminMenuQueryWrapper.eq("menu_level", "1").eq("menu_status", "1").orderByAsc("menu_index");
        List<AdminMenu> parentAdminMenuList = adminMenuService.list(adminMenuQueryWrapper);

        // 绑定数据到前台
        model.addAttribute("menuLevelDictList", menuLevelDictList);
        model.addAttribute("menuStatusDictList", menuStatusDictList);
        model.addAttribute("parentAdminMenuList", parentAdminMenuList);

        // 返回页面
        return "Admin/Menu/add";
    }

    /**
     * 新增菜单
     *
     * @param data    前端表单数据
     * @param session session
     * @return 返回新增结果
     */
    @RequestMapping("add.do")
    @ResponseBody
    public AppResponse<AdminMenu> add(@RequestParam String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为对象
        Gson gson = new Gson();
        AdminMenu adminMenu = gson.fromJson(data, new TypeToken<AdminMenu>() {
        }.getType());

        // 菜单编号
        adminMenu.setMenuNo(CommonFunctions.generateNo("AMNO"));
        // 操作信息
        adminMenu.setAddUserNo(adminUser.getUserNo());
        adminMenu.setAddTime(new Date());
        adminMenu.setUpdateUserNo(adminUser.getUserNo());
        adminMenu.setUpdateTime(new Date());

        // 新增数据
        boolean rel = adminMenuService.save(adminMenu);
        if (rel) {
            return AppResponse.success("菜单新增成功！");
        }

        // 操作失败
        return AppResponse.fail("菜单新增失败，请刷新页面后重新提交！");
    }

    /**
     * 编辑页面显示
     *
     * @return 返回编辑页面
     */
    @RequestMapping("edit_page.do")
    public String editPage(String menuNo, Model model) {
        // 查询菜单
        QueryWrapper<AdminMenu> adminMenuQueryWrapper = new QueryWrapper<>();
        adminMenuQueryWrapper.eq("menu_no", menuNo);
        AdminMenu adminMenu = adminMenuService.getOne(adminMenuQueryWrapper);

        // 菜单等级
        List<AdminDict> menuLevelDictList = dictService.getDictListByDictName("menuLevel");
        // 菜单状态
        List<AdminDict> menuStatusDictList = dictService.getDictListByDictName("menuStatus");

        // 所有启用的父级菜单列表
        QueryWrapper<AdminMenu> adminMenuListQueryWrapper = new QueryWrapper<>();
        adminMenuListQueryWrapper.eq("menu_level", "1").eq("menu_status", "1").orderByAsc("menu_index");
        List<AdminMenu> parentAdminMenuList = adminMenuService.list(adminMenuListQueryWrapper);

        // 查询是否有子菜单
        QueryWrapper<AdminMenu> chileAdminMenuQueryWrapper = new QueryWrapper<>();
        chileAdminMenuQueryWrapper.eq("parent_menu_no", adminMenu.getMenuNo());
        List<AdminMenu> chileAdminMenuList = adminMenuService.list(chileAdminMenuQueryWrapper);
        boolean hasChild = !chileAdminMenuList.isEmpty();

        // 绑定数据到前台
        model.addAttribute("adminMenu", adminMenu);
        model.addAttribute("menuLevelDictList", menuLevelDictList);
        model.addAttribute("menuStatusDictList", menuStatusDictList);
        model.addAttribute("parentAdminMenuList", parentAdminMenuList);
        model.addAttribute("hasChild", hasChild);

        // 返回页面
        return "Admin/Menu/edit";
    }

    /**
     * 编辑菜单
     *
     * @param data    表单数据
     * @param session session
     * @return 返回编辑修改结果
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AppResponse<AdminMenu> edit(String data, HttpSession session) {
        // 前台字符串数据转化为对象
        Gson gson = new Gson();
        AdminMenu adminMenu = gson.fromJson(data, new TypeToken<AdminMenu>() {
        }.getType());

        // 更新数据
        boolean rel = adminMenuService.updateById(adminMenu);
        if (rel) {
            return AppResponse.success("菜单编辑成功！");
        }

        // 操作失败，返回
        return AppResponse.fail("菜单编辑失败，请刷新页面后重新提交！");
    }

    /**
     * 启用菜单
     *
     * @param menuNo 菜单编号
     * @return 返回操作结果
     */
    @RequestMapping("enable_menu.do")
    @ResponseBody
    public AppResponse<AdminMenu> enableMenu(String menuNo) {
        // 根据菜单编号查询
        QueryWrapper<AdminMenu> adminMenuQueryWrapper = new QueryWrapper<>();
        adminMenuQueryWrapper.eq("menu_no", menuNo).eq("menu_status", "0");
        AdminMenu adminMenu = adminMenuService.getOne(adminMenuQueryWrapper);

        // 判断菜单是否存在
        if (null == adminMenu) {
            return AppResponse.fail("启用菜单失败，请刷新页面后重新操作！");
        } else {
            adminMenu.setMenuStatus("1");
            adminMenuService.updateById(adminMenu);
            return AppResponse.success("启用菜单成功！");
        }
    }

    /**
     * 禁用菜单
     *
     * @param menuNo 菜单编号
     * @return 返回操作结果
     */
    @RequestMapping("disable_menu.do")
    @ResponseBody
    public AppResponse<AdminMenu> disableMenu(String menuNo) {
        // 根据菜单编号查询
        QueryWrapper<AdminMenu> adminMenuQueryWrapper = new QueryWrapper<>();
        adminMenuQueryWrapper.eq("menu_no", menuNo).eq("menu_status", "1");
        AdminMenu adminMenu = adminMenuService.getOne(adminMenuQueryWrapper);

        // 判断菜单是否存在
        if (null == adminMenu) {
            return AppResponse.fail("禁用菜单失败，请刷新页面后重新操作！");
        } else {
            // 检查是否存在在子菜单
            QueryWrapper<AdminMenu> chileAdminMenuQueryWrapper = new QueryWrapper<>();
            chileAdminMenuQueryWrapper.eq("parent_menu_no", adminMenu.getMenuNo());
            List<AdminMenu> chileAdminMenuList = adminMenuService.list(chileAdminMenuQueryWrapper);

            // 如果没有子菜单，允许删除，否则不允许删除
            if (chileAdminMenuList.isEmpty()) {
                adminMenu.setMenuStatus("0");
                adminMenuService.updateById(adminMenu);
                return AppResponse.success("禁用菜单成功！");
            } else {
                return AppResponse.fail("禁用菜单失败，该菜单下存在子菜单！");
            }
        }
    }

    /**
     * 删除菜单
     *
     * @param menuNo 菜单编号
     * @return 返回操作结果
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public AppResponse<AdminMenu> deleteMenu(String menuNo) {
        // 根据菜单编号查询
        QueryWrapper<AdminMenu> adminMenuQueryWrapper = new QueryWrapper<>();
        adminMenuQueryWrapper.eq("menu_no", menuNo);
        AdminMenu adminMenu = adminMenuService.getOne(adminMenuQueryWrapper);

        // 判断菜单是否存在
        if (null == adminMenu) {
            return AppResponse.fail("删除菜单失败，请刷新页面后重新操作！");
        } else {
            // 检查是否存在在子菜单
            QueryWrapper<AdminMenu> chileAdminMenuQueryWrapper = new QueryWrapper<>();
            chileAdminMenuQueryWrapper.eq("parent_menu_no", adminMenu.getMenuNo());
            List<AdminMenu> chileAdminMenuList = adminMenuService.list(chileAdminMenuQueryWrapper);

            // 如果没有子菜单，允许删除，否则不允许删除
            if (chileAdminMenuList.isEmpty()) {
                adminMenuService.removeById(adminMenu);
                return AppResponse.success("删除菜单成功！");
            } else {
                return AppResponse.fail("删除菜单失败，该菜单下存在子菜单！");
            }
        }
    }

    /**
     * 更新菜单排序
     *
     * @param menuNo    菜单编号
     * @param menuIndex 菜单排序
     * @param session   session
     * @return 返回操作结果
     */
    @RequestMapping("update_index.do")
    @ResponseBody
    public AppResponse<AdminMenu> updateIndex(String menuNo, String menuIndex, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 根据菜单编号查询
        QueryWrapper<AdminMenu> adminMenuQueryWrapper = new QueryWrapper<>();
        adminMenuQueryWrapper.eq("menu_no", menuNo);
        AdminMenu adminMenu = adminMenuService.getOne(adminMenuQueryWrapper);

        boolean a = StringUtils.isNumeric(menuIndex);
        boolean b = 4 == menuIndex.length();
        // 判断是不是数字
        if (StringUtils.isNumeric(menuIndex) && 4 == menuIndex.length()) {
            adminMenu.setMenuIndex(menuIndex);
            adminMenu.setUpdateUserNo(adminUser.getUserNo());
            adminMenu.setUpdateTime(new Date());
            // 更新
            adminMenuService.updateById(adminMenu);
            // 返回结果
            return AppResponse.success("索引修改成功！");
        }

        // 更新失败
        return AppResponse.fail("索引修改失败！");
    }
}
