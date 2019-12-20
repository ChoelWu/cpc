package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/17
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 后端角色管理
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.AdminAuth;
import com.joe.entity.AdminMenu;
import com.joe.entity.AdminRole;
import com.joe.pojo.AdminMenuPOJO;
import com.joe.pojo.AuthPOJO;
import com.joe.pojo.Menu;
import com.joe.pojo.Page;
import com.joe.service.common.PageService;
import com.joe.service.system.AdminAuthService;
import com.joe.service.system.AdminMenuService;
import com.joe.service.system.AdminRoleService;
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
@RequestMapping("/admin/role")
public class AdminRoleController {
    private Logger logger = LoggerFactory.getLogger(AdminRoleController.class);

    @Resource
    private AdminRoleService adminRoleService;

    @Resource
    private PageService pageService;

    @Resource
    private AdminMenuService adminMenuService;

    @Resource
    private AdminAuthService adminAuthService;

    /**
     * 列表页面显示
     *
     * @return 返回页面视图
     */
    @RequestMapping("view.do")
    public String view(Model model, @RequestParam(required = false, defaultValue = "1") int currentPage, @RequestParam(required = false) String conditions) {
        // 查询数据条数
        QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();

        Gson gson = new Gson();
        Map<String, String> conditionMap = gson.fromJson(conditions, new TypeToken<Map<String, String>>() {
        }.getType());

        // 角色名模糊查询
        if (null != conditionMap) {
            if (conditionMap.containsKey("roleName") && StringUtils.isNoneEmpty(conditionMap.get("roleName"))) {
                adminRoleQueryWrapper.like("role_name", conditionMap.get("roleName"));
            }
            if (conditionMap.containsKey("roleStatus") && StringUtils.isNoneEmpty(conditionMap.get("roleStatus"))) {
                adminRoleQueryWrapper.eq("role_status", conditionMap.get("roleStatus"));
            }
        }
        // 查询条数
        int adminRoleNum = adminRoleService.count(adminRoleQueryWrapper);

        // 分页
        Page page = pageService.Pagination(currentPage, adminRoleNum);

        // 获取用户列表
        int start = page.getRecordNum() * (page.getCurrentPage() - 1);
        adminRoleQueryWrapper.orderByAsc("role_no").last("limit " + start + "," + page.getRecordNum());
        List<AdminRole> adminRoleList = adminRoleService.list(adminRoleQueryWrapper);

        // 绑定数据
        model.addAttribute("adminRoleList", adminRoleList);
        model.addAttribute("page", page);
        model.addAttribute("conditions", conditionMap);

        // 返回视图页面
        return "Admin/Role/index";
    }

    /**
     * 角色新增页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("add_page.do")
    public String addPage() {
        // 返回页面视图
        return "Admin/Role/add";
    }

    /**
     * 添加用户
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("add.do")
    @ResponseBody
    public AppResponse<AdminRole> add(String data, HttpSession session) {
        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        AdminRole adminRole = gson.fromJson(data, new TypeToken<AdminRole>() {
        }.getType());

        // 角色编号
        adminRole.setRoleNo(CommonFunctions.generateNo("ARNO"));

        // 角色状态-未激活
        adminRole.setRoleStatus("1");

        // 操作信息
        adminRole.setAddUserNo("1");
        adminRole.setAddTime(new Date());
        adminRole.setUpdateUserNo("1");
        adminRole.setUpdateTime(new Date());

        // 新增数据
        boolean rel = adminRoleService.save(adminRole);
        if (rel) {
            return AppResponse.success("角色新增成功！");
        }

        // 操作失败
        return AppResponse.fail("角色新增失败，请刷新页面后重新提交！");
    }

    /**
     * 角色编辑页面
     *
     * @param model  model
     * @param roleNo 角色编号
     * @return 返回页面视图
     */
    @RequestMapping("edit_page.do")
    public String editPage(Model model, String roleNo) {
        // 根据用户编号查询
        QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
        adminRoleQueryWrapper.eq("role_no", roleNo);
        AdminRole adminRole = adminRoleService.getOne(adminRoleQueryWrapper);

        // 绑定数据
        model.addAttribute("adminRole", adminRole);

        return "Admin/Role/edit";
    }

    /**
     * 编辑角色
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AppResponse<AdminRole> edit(String data, HttpSession session) {
        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        AdminRole adminRole = gson.fromJson(data, new TypeToken<AdminRole>() {
        }.getType());

        // 更新操作信息
        adminRole.setUpdateUserNo("1l");
        adminRole.setUpdateTime(new Date());

        // 更新数据
        boolean rel = adminRoleService.updateById(adminRole);
        if (rel) {
            return AppResponse.success("角色编辑成功！");
        }

        // 操作做失败
        return AppResponse.fail("角色编辑失败，请刷新页面后重新提交！");
    }

    /**
     * 删除角色
     *
     * @param roleNo 角色编号
     * @return 返回操作结果
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public AppResponse<AdminRole> deleteRole(String roleNo) {
        // 根据角色编号查询
        QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
        adminRoleQueryWrapper.eq("role_no", roleNo);
        AdminRole adminRole = adminRoleService.getOne(adminRoleQueryWrapper);

        // 判断角色是否存在
        if (null == adminRole) {
            return AppResponse.fail("删除角色失败，请刷新页面后重新操作！");
        } else {
            adminRoleService.removeById(adminRole);
            return AppResponse.success("删除角色成功！");
        }
    }

    /**
     * 批量删除
     *
     * @param roleNos 用户编号串
     * @return 返回操作结果
     */
    @RequestMapping("/delete_batch.do")
    @ResponseBody
    public AppResponse<AdminRole> deleteBatch(String roleNos) {
        String[] roleNoArr = roleNos.split(",");

        List<Long> roleIdList = Lists.newArrayList();

        // 查询要删除的用户列表
        for (String roleNo : roleNoArr) {
            QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
            adminRoleQueryWrapper.eq("role_no", roleNo);
            AdminRole adminRole = adminRoleService.getOne(adminRoleQueryWrapper);
            if (null == adminRole) {
                return AppResponse.fail("删除角色失败，请刷新页面后重新操作！");
            } else {
                roleIdList.add(adminRole.getRoleId());
            }
        }

        // 删除用户
        if (roleIdList.size() == roleNoArr.length) {
            adminRoleService.removeByIds(roleIdList);
            return AppResponse.success("删除角色成功！");
        } else {
            return AppResponse.fail("删除角色失败，请刷新页面后重新操作！");
        }
    }

    /**
     * 启用角色
     *
     * @param roleNo 角色编号
     * @return 返回操作结果
     */
    @RequestMapping("/enable.do")
    @ResponseBody
    public AppResponse<AdminRole> enable(String roleNo) {
        // 查询角色
        QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
        adminRoleQueryWrapper.eq("role_no", roleNo).eq("role_status", "0");
        AdminRole adminRole = adminRoleService.getOne(adminRoleQueryWrapper);

        // 更改状态为未激活
        adminRole.setRoleStatus("1");

        // 更新状态
        adminRoleService.updateById(adminRole);
        return AppResponse.success("启用角色成功！");
    }

    /**
     * 禁用角色
     *
     * @param roleNo 角色编号
     * @return 返回操作结果
     */
    @RequestMapping("/disable.do")
    @ResponseBody
    public AppResponse<AdminRole> disable(String roleNo) {
        // 查询角色
        QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
        adminRoleQueryWrapper.eq("role_no", roleNo).ne("role_status", "0");
        AdminRole adminRole = adminRoleService.getOne(adminRoleQueryWrapper);

        // 更改状态为未激活
        adminRole.setRoleStatus("0");

        // 更新状态
        adminRoleService.updateById(adminRole);
        return AppResponse.success("禁用角色成功！");
    }

    /**
     * 权限列表
     *
     * @param roleNo 角色编号
     * @param model  model
     * @return 返回页面视图
     */
    @RequestMapping("/auth_page.do")
    public String authPage(String roleNo, Model model) {
        // 查询出所有的菜单
        QueryWrapper<AdminMenu> adminMenuQueryWrapper = new QueryWrapper<>();
        adminMenuQueryWrapper.orderByAsc("menu_level", "menu_index");
        List<AdminMenu> adminMenuList = adminMenuService.list(adminMenuQueryWrapper);

        // 创建前台输出的权限列表
        List<AuthPOJO> authPOJOList = Lists.newArrayList();

        // 循环遍历菜单，生成前台格式
        for (AdminMenu adminMenu : adminMenuList) {
            if (StringUtils.equals("1", adminMenu.getMenuLevel())) {
                // 创建菜单权限
                AuthPOJO authPOJO = new AuthPOJO();

                // 子菜单权限列表
                List<AdminMenuPOJO> adminMenuPOJOList = Lists.newArrayList();

                // 循环组织子菜单
                for (AdminMenu adminChildMenu : adminMenuList) {
                    AdminMenuPOJO adminMenuPOJO = new AdminMenuPOJO();
                    if (StringUtils.equals(adminMenu.getMenuNo(), adminChildMenu.getParentMenuNo())) {
                        QueryWrapper<AdminAuth> adminAuthQueryWrapper = new QueryWrapper<>();
                        adminAuthQueryWrapper.eq("menu_no", adminChildMenu.getMenuNo());
                        List<AdminAuth> adminAuthList = adminAuthService.list(adminAuthQueryWrapper);

                        adminMenuPOJO.setMenu(adminChildMenu);
                        adminMenuPOJO.setAdminAuthList(adminAuthList);
                        adminMenuPOJOList.add(adminMenuPOJO);
                    }
                }

                authPOJO.setChildMenuPOJOList(adminMenuPOJOList);
                authPOJO.setParentMenu(adminMenu);
            }
        }

        // 绑定数据
        model.addAttribute("authPOJOList", authPOJOList);

        // 返回页面视图
        return "/Admin/Role/auth_page";
    }
}
