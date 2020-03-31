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
import com.joe.entity.*;
import com.joe.pojo.Page;
import com.joe.pojo.Tree;
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
        Page page = pageService.Pagination(currentPage, adminRoleNum, 10);

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
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        AdminRole adminRole = gson.fromJson(data, new TypeToken<AdminRole>() {
        }.getType());

        // 角色编号
        adminRole.setRoleNo(CommonFunctions.generateNo("ARNO"));

        // 角色状态-未激活
        adminRole.setRoleStatus("1");

        // 操作信息
        adminRole.setAddUserNo(adminUser.getUserNo());
        adminRole.setAddTime(new Date());
        adminRole.setUpdateUserNo(adminUser.getUserNo());
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
        // 获取 session
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        AdminRole adminRole = gson.fromJson(data, new TypeToken<AdminRole>() {
        }.getType());

        // 更新操作信息
        adminRole.setUpdateUserNo(adminUser.getUserNo());
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
    public String authPage(String roleNo, Model model, HttpSession session) {
        // 查询出当前用户角色
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
        QueryWrapper<AdminRole> currentAdminRoleQueryWrapper = new QueryWrapper<>();
        currentAdminRoleQueryWrapper.eq("role_no", adminUser.getRoleNo());
        AdminRole currentAdminRole = adminRoleService.getOne(currentAdminRoleQueryWrapper);

        // 查询出已经有的权限
        QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
        adminRoleQueryWrapper.eq("role_no", roleNo);
        AdminRole adminRole = adminRoleService.getOne(adminRoleQueryWrapper);

        String authString = adminRole.getRoleAuth();

        // 查询出所有的菜单
        QueryWrapper<AdminMenu> adminMenuQueryWrapper = new QueryWrapper<>();
        adminMenuQueryWrapper.orderByAsc("menu_level", "menu_index");
        List<AdminMenu> adminMenuList = adminMenuService.list(adminMenuQueryWrapper);

        List<Tree> level1TreeList = Lists.newArrayList();
        for (AdminMenu adminMenu : adminMenuList) {
            if (StringUtils.equals("1", adminMenu.getMenuLevel())) {
                Tree level1Tree = new Tree();
                level1Tree.setTitle(adminMenu.getMenuName());
                level1Tree.setId(adminMenu.getMenuNo());
                level1Tree.setField(adminMenu.getMenuNo());

                List<Tree> level2TreeList = Lists.newArrayList();
                for (AdminMenu adminChildMenu : adminMenuList) {
                    if (StringUtils.equals(adminMenu.getMenuNo(), adminChildMenu.getParentMenuNo())) {
                        Tree level2Tree = new Tree();
                        level2Tree.setTitle(adminChildMenu.getMenuName());
                        level2Tree.setId(adminChildMenu.getMenuNo());
                        level2Tree.setField(adminChildMenu.getMenuNo());

                        QueryWrapper<AdminAuth> adminAuthQueryWrapper = new QueryWrapper<>();
                        adminAuthQueryWrapper.eq("menu_no", adminChildMenu.getMenuNo());
                        List<AdminAuth> adminAuthList = adminAuthService.list(adminAuthQueryWrapper);

                        List<Tree> level3TreeList = Lists.newArrayList();
                        for (AdminAuth adminAuth : adminAuthList) {
                            Tree level3Tree = new Tree();

                            level3Tree.setTitle(adminAuth.getAuthName());
                            level3Tree.setId(adminAuth.getAuthNo());
                            level3Tree.setField(adminAuth.getAuthNo());

                            // 如果当前循环到的权限已经存在，将checked属性设置为true
                            if (StringUtils.contains("," + authString + ",", "," + adminAuth.getAuthNo() + ",")) {
                                level3Tree.setChecked(true);
                            }

                            // 只显示当前用户拥有的权限：如果当前登录的用户是超级账户，直接显示所有权限，否则过滤权限
                            if (1 == currentAdminRole.getRoleId()) {
                                level3TreeList.add(level3Tree);
                            } else {
                                if (StringUtils.contains("," + currentAdminRole.getRoleAuth() + ",", "," + adminAuth.getAuthNo() + ",")) {
                                    level3TreeList.add(level3Tree);
                                }
                            }
                        }

                        // 只有分支存在时才加入树中
                        if (!level3TreeList.isEmpty()) {
                            level2Tree.setChildren(level3TreeList);
                            level2TreeList.add(level2Tree);
                        }
                    }
                }

                // 只有分支存在时才加入树中
                if (!level2TreeList.isEmpty()) {
                    level1Tree.setChildren(level2TreeList);
                    level1TreeList.add(level1Tree);
                }
            }
        }

        // 转化为 json 字符串
        Gson gson = new Gson();
        String tree = gson.toJson(level1TreeList);

        // 绑定数据
        model.addAttribute("tree", tree);
        model.addAttribute("roleNo", roleNo);

        // 返回页面视图
        return "/Admin/Role/auth_page";
    }

    /**
     * 修改/分配权限
     *
     * @param auth    权限字符串
     * @param roleNo  角色NO
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("/edit_auth.do")
    @ResponseBody
    public AppResponse<AdminRole> editAuth(String auth, String roleNo, HttpSession session) {
        // session 信息
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 将前端的数据转化为实例
        Gson gson = new Gson();
        List<Tree> treeList = gson.fromJson(auth, new TypeToken<List<Tree>>() {
        }.getType());

        // 循环实例，生成权限编号list
        List<String> authNoList = Lists.newArrayList();
        for (Tree tree : treeList) {
            if (null != tree.getChildren()) {
                for (Tree childTree : tree.getChildren()) {
                    if (null != childTree.getChildren()) {
                        for (Tree babyTree : childTree.getChildren()) {
                            authNoList.add(babyTree.getId());
                        }
                    }
                }
            }
        }

        // 更新角色权限
        String authNoString = StringUtils.join(authNoList, ",");

        QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
        adminRoleQueryWrapper.eq("role_no", roleNo);
        AdminRole adminRole = adminRoleService.getOne(adminRoleQueryWrapper);

        adminRole.setRoleAuth(authNoString);
        adminRole.setUpdateUserNo(adminUser.getUserNo());
        adminRole.setUpdateTime(new Date());
        adminRoleService.updateById(adminRole);

        // 返回操作结果
        return AppResponse.success("权限分配成功！");
    }
}
