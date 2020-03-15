package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/17
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 后端权限管理
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.AdminAuth;
import com.joe.entity.AdminMenu;
import com.joe.entity.AdminUser;
import com.joe.pojo.Auth;
import com.joe.pojo.Menu;
import com.joe.pojo.Page;
import com.joe.service.common.PageService;
import com.joe.service.system.AdminAuthService;
import com.joe.service.system.AdminMenuService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/auth")
public class AdminAuthController {
    private Logger logger = LoggerFactory.getLogger(AdminAuthController.class);

    @Resource
    private AdminAuthService adminAuthService;

    @Resource
    private PageService pageService;

    @Resource
    private AdminMenuService adminMenuService;

    /**
     * 列表页面显示
     *
     * @return 返回页面视图
     */
    @RequestMapping("view.do")
    public String view(Model model, @RequestParam(required = false, defaultValue = "1") int currentPage, @RequestParam(required = false) String conditions) {
        Gson gson = new Gson();
        Map<String, Object> conditionMap = gson.fromJson(conditions, new TypeToken<Map<String, Object>>() {
        }.getType());

        // 查询条数
        int adminAuthNum = adminAuthService.countAuth(conditionMap);

        // 分页
        Page page = pageService.Pagination(currentPage, adminAuthNum, 10);

        // 获取用户列表
        int start = page.getRecordNum() * (page.getCurrentPage() - 1);
        Map<String, Integer> pageInfoMap = Maps.newHashMap();
        pageInfoMap.put("startRow", start);
        pageInfoMap.put("rowNum", page.getRecordNum());
        List<Auth> authList = adminAuthService.listAuth(conditionMap, pageInfoMap);

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

        // 绑定数据
        model.addAttribute("authList", authList);
        model.addAttribute("menuList", menuList);
        model.addAttribute("page", page);
        model.addAttribute("conditions", conditionMap);

        return "Admin/Auth/index";
    }

    /**
     * 权限新增页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("add_page.do")
    public String addPage(Model model) {
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

        model.addAttribute("menuList", menuList);
        // 返回页面视图
        return "Admin/Auth/add";
    }

    /**
     * 添加权限
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("add.do")
    @ResponseBody
    public AppResponse<AdminAuth> add(String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        AdminAuth adminAuth = gson.fromJson(data, new TypeToken<AdminAuth>() {
        }.getType());

        // 权限编号
        adminAuth.setAuthNo(CommonFunctions.generateNo("AUNO"));

        // 操作信息
        adminAuth.setAddUserNo(adminUser.getUserNo());
        adminAuth.setAddTime(new Date());
        adminAuth.setUpdateUserNo(adminUser.getUserNo());
        adminAuth.setUpdateTime(new Date());

        // 新增数据
        boolean rel = adminAuthService.save(adminAuth);
        if (rel) {
            return AppResponse.success("权限新增成功！");
        }

        // 操作失败
        return AppResponse.fail("权限新增失败，请刷新页面后重新提交！");
    }

    /**
     * 权限编辑页面
     *
     * @param model  model
     * @param authNo 权限编号
     * @return 返回页面视图
     */
    @RequestMapping("edit_page.do")
    public String editPage(Model model, String authNo) {
        // 根据权限编号查询
        QueryWrapper<AdminAuth> adminAuthQueryWrapper = new QueryWrapper<>();
        adminAuthQueryWrapper.eq("auth_no", authNo);
        AdminAuth adminAuth = adminAuthService.getOne(adminAuthQueryWrapper);

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

        // 绑定数据
        model.addAttribute("menuList", menuList);
        model.addAttribute("adminAuth", adminAuth);

        return "Admin/Auth/edit";
    }

    /**
     * 编辑权限
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AppResponse<AdminAuth> edit(String data, HttpSession session) {
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        AdminAuth adminAuth = gson.fromJson(data, new TypeToken<AdminAuth>() {
        }.getType());

        // 更新操作信息
        adminAuth.setUpdateUserNo(adminUser.getUserNo());
        adminAuth.setUpdateTime(new Date());

        // 更新数据
        boolean rel = adminAuthService.updateById(adminAuth);
        if (rel) {
            return AppResponse.success("权限编辑成功！");
        }

        // 操作做失败
        return AppResponse.fail("权限编辑失败，请刷新页面后重新提交！");
    }

    /**
     * 删除权限
     *
     * @param authNo 权限编号
     * @return 返回操作结果
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public AppResponse<AdminAuth> deleteAuth(String authNo) {
        // 根据权限编号查询
        QueryWrapper<AdminAuth> adminAuthQueryWrapper = new QueryWrapper<>();
        adminAuthQueryWrapper.eq("auth_no", authNo);
        AdminAuth adminAuth = adminAuthService.getOne(adminAuthQueryWrapper);

        // 判断权限是否存在
        if (null == adminAuth) {
            return AppResponse.fail("删除权限失败，请刷新页面后重新操作！");
        } else {
            adminAuthService.removeById(adminAuth);
            return AppResponse.success("删除权限成功！");
        }
    }

    /**
     * 批量删除
     *
     * @param authNos 权限编号串
     * @return 返回操作结果
     */
    @RequestMapping("/delete_batch.do")
    @ResponseBody
    public AppResponse<AdminAuth> deleteBatch(String authNos) {
        String[] authNoArr = authNos.split(",");

        List<Long> authIdList = Lists.newArrayList();

        // 查询要删除的权限列表
        for (String authNo : authNoArr) {
            QueryWrapper<AdminAuth> adminAuthQueryWrapper = new QueryWrapper<>();
            adminAuthQueryWrapper.eq("auth_no", authNo);
            AdminAuth adminAuth = adminAuthService.getOne(adminAuthQueryWrapper);
            if (null == adminAuth) {
                return AppResponse.fail("删除权限失败，请刷新页面后重新操作！");
            } else {
                authIdList.add(adminAuth.getAuthId());
            }
        }

        // 删除权限
        if (authIdList.size() == authNoArr.length) {
            adminAuthService.removeByIds(authIdList);
            return AppResponse.success("删除权限成功！");
        } else {
            return AppResponse.fail("删除权限失败，请刷新页面后重新操作！");
        }
    }
}
