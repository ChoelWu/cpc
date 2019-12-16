package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/17
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 管理端用户
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.AdminRole;
import com.joe.entity.AdminUser;
import com.joe.entity.IndexUser;
import com.joe.pojo.AdminUserPOJO;
import com.joe.pojo.Page;
import com.joe.service.common.PageService;
import com.joe.service.system.AdminRoleService;
import com.joe.service.system.AdminUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    private Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private PageService pageService;

    @Resource
    private AdminRoleService adminRoleService;

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
        int adminUserNum = adminUserService.countAdminUser(conditionMap);

        // 分页
        Page page = pageService.Pagination(currentPage, adminUserNum);

        // 获取用户列表
        int start = page.getRecordNum() * (page.getCurrentPage() - 1);
        Map<String, Integer> pageInfoMap = Maps.newHashMap();
        pageInfoMap.put("startRow", start);
        pageInfoMap.put("rowNum", page.getRecordNum());
        List<AdminUserPOJO> adminUserPOJOList = adminUserService.listAdminUser(conditionMap, pageInfoMap);

        // 绑定数据
        model.addAttribute("adminUserList", adminUserPOJOList);
        model.addAttribute("page", page);
        model.addAttribute("conditions", conditionMap);

        return "Admin/User/index";
    }

    /**
     * 用户新增页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("add_page.do")
    public String addPage(Model model) {
        // 获取角色列表
        QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
        adminRoleQueryWrapper.eq("role_status", "1");
        List<AdminRole> adminRoleList = adminRoleService.list(adminRoleQueryWrapper);

        // 绑定数据
        model.addAttribute("adminRoleList", adminRoleList);

        // 返回页面视图
        return "Admin/User/add";
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
    public AppResponse<AdminUser> add(String data, HttpSession session) {
        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        AdminUser adminUser = gson.fromJson(data, new TypeToken<AdminUser>() {
        }.getType());

        // 用户编号
        adminUser.setUserNo(CommonFunctions.generateNo("AUNO"));

        // 用户密码
        adminUser.setUserPassword(CommonFunctions.encryptPassword("000000", adminUser.getUserNo()));

        // 用户状态-未激活
        adminUser.setUserStatus("1");

        // 操作信息
        adminUser.setAddUserNo("1");
        adminUser.setAddTime(new Date());
        adminUser.setUpdateUserNo("1");
        adminUser.setUpdateTime(new Date());

        // 新增数据
        boolean rel = adminUserService.save(adminUser);
        if (rel) {
            return AppResponse.success("用户新增成功！");
        }

        // 操作失败
        return AppResponse.fail("用户新增失败，请刷新页面后重新提交！");
    }

    /**
     * 用户编辑页面
     *
     * @param model  model
     * @param userNo 用户编号
     * @return 返回页面视图
     */
    @RequestMapping("edit_page.do")
    public String editPage(Model model, String userNo) {
        // 根据用户编号查询
        QueryWrapper<AdminUser> adminUserQueryWrapper = new QueryWrapper<>();
        adminUserQueryWrapper.eq("user_no", userNo);
        AdminUser adminUser = adminUserService.getOne(adminUserQueryWrapper);

        // 获取角色列表
        QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
        adminRoleQueryWrapper.eq("role_status", "1");
        List<AdminRole> adminRoleList = adminRoleService.list(adminRoleQueryWrapper);

        // 绑定数据
        model.addAttribute("adminRoleList", adminRoleList);
        model.addAttribute("adminUser", adminUser);

        return "Admin/User/edit";
    }

    /**
     * 编辑用户
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("edit.do")
    @ResponseBody
    public AppResponse<AdminUser> edit(String data, HttpSession session) {
        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        AdminUser adminUser = gson.fromJson(data, new TypeToken<AdminUser>() {
        }.getType());

        // 更新操作信息
        adminUser.setUpdateUserNo("1l");
        adminUser.setUpdateTime(new Date());

        // 更新数据
        boolean rel = adminUserService.updateById(adminUser);
        if (rel) {
            return AppResponse.success("用户编辑成功！");
        }

        // 操作做失败
        return AppResponse.fail("用户编辑失败，请刷新页面后重新提交！");
    }

    /**
     * 删除用户
     *
     * @param userNo 用户编号
     * @return 返回操作结果
     */
    @RequestMapping("delete.do")
    @ResponseBody
    public AppResponse<AdminUser> deleteUser(String userNo) {
        // 根据用户编号查询
        QueryWrapper<AdminUser> adminUserQueryWrapper = new QueryWrapper<>();
        adminUserQueryWrapper.eq("user_no", userNo);
        AdminUser adminUser = adminUserService.getOne(adminUserQueryWrapper);

        // 判断用户是否存在
        if (null == adminUser) {
            return AppResponse.fail("删除用户失败，请刷新页面后重新操作！");
        } else {
            adminUserService.removeById(adminUser);
            return AppResponse.success("删除用户成功！");
        }
    }

    /**
     * 批量删除
     *
     * @param userNos 用户编号串
     * @return 返回操作结果
     */
    @RequestMapping("/delete_batch.do")
    @ResponseBody
    public AppResponse<AdminUser> deleteBatch(String userNos) {
        String[] userNoArr = userNos.split(",");

        List<Long> userIdList = Lists.newArrayList();

        // 查询要删除的用户列表
        for (String userNo : userNoArr) {
            QueryWrapper<AdminUser> adminUserQueryWrapper = new QueryWrapper<>();
            adminUserQueryWrapper.eq("user_no", userNo);
            AdminUser adminUser = adminUserService.getOne(adminUserQueryWrapper);
            if (null == adminUser) {
                return AppResponse.fail("删除用户失败，请刷新页面后重新操作！");
            } else {
                userIdList.add(adminUser.getUserId());
            }
        }

        // 删除用户
        if (userIdList.size() == userNoArr.length) {
            adminUserService.removeByIds(userIdList);
            return AppResponse.success("删除用户成功！");
        } else {
            return AppResponse.fail("删除用户失败，请刷新页面后重新操作！");
        }
    }

    /**
     * 启用用户
     *
     * @param userNo 用户编号
     * @return 返回操作结果
     */
    @RequestMapping("/enable.do")
    @ResponseBody
    public AppResponse<AdminUser> enable(String userNo) {
        // 查询用户
        QueryWrapper<AdminUser> adminUserQueryWrapper = new QueryWrapper<>();
        adminUserQueryWrapper.eq("user_no", userNo).eq("user_status", "0");
        AdminUser adminUser = adminUserService.getOne(adminUserQueryWrapper);

        // 更改状态为未激活
        adminUser.setUserStatus("1");

        // 更新状态
        adminUserService.updateById(adminUser);
        return AppResponse.success("启用用户成功，请通知用户激活账户！");
    }

    /**
     * 禁用用户
     *
     * @param userNo 用户编号
     * @return 返回操作结果
     */
    @RequestMapping("/disable.do")
    @ResponseBody
    public AppResponse<AdminUser> disable(String userNo) {
        // 查询用户
        QueryWrapper<AdminUser> adminUserQueryWrapper = new QueryWrapper<>();
        adminUserQueryWrapper.eq("user_no", userNo).ne("user_status", "0");
        AdminUser adminUser = adminUserService.getOne(adminUserQueryWrapper);

        // 更改状态为未激活
        adminUser.setUserStatus("0");

        // 更新状态
        adminUserService.updateById(adminUser);
        return AppResponse.success("禁用用户成功！");
    }

    /**
     * 重置密码
     *
     * @param userNo 用户编号
     * @return 返回操作结果
     */
    @RequestMapping("/recovery_password.do")
    @ResponseBody
    public AppResponse<AdminUser> recoveryPassword(String userNo) {
        // 查询用户
        QueryWrapper<AdminUser> adminUserQueryWrapper = new QueryWrapper<>();
        adminUserQueryWrapper.eq("user_no", userNo).ne("user_status", "0");
        AdminUser adminUser = adminUserService.getOne(adminUserQueryWrapper);

        // 更改密码为 000000
        adminUser.setUserPassword(CommonFunctions.encryptPassword("000000", adminUser.getUserNo()));

        // 更新密码
        adminUserService.updateById(adminUser);
        return AppResponse.success("重置密码成功！");
    }

    /**
     * 个人信息页面
     *
     * @param model   model
     * @param session session
     * @return 返回页面视图
     */
    @RequestMapping("/personal_page.do")
    public String personalPage(Model model, HttpSession session) {
        // 查询出用户信息
        Map<String, Object> condition = Maps.newHashMap();
        condition.put("userNo", "AUNO20191125122211P11301");
        AdminUserPOJO adminUserPOJO = adminUserService.getAdminUser(condition);

        // 数据绑定
        model.addAttribute("adminUser", adminUserPOJO);

        return "Admin/User/personal";
    }

    /**
     * 图片上传
     *
     * @param userHeadSculpture 文件
     * @param request           request
     * @return 返回上传结果
     */
    @RequestMapping("/upload_pic.do")
    @ResponseBody
    public AppResponse<String> uploadPic(MultipartFile userHeadSculpture, HttpServletRequest request) {
        if (!userHeadSculpture.isEmpty()) {
            // 获取文件名
            String originFileName = userHeadSculpture.getOriginalFilename();
            String fileName = CommonFunctions.getRandomFileName(originFileName.substring(originFileName.lastIndexOf(".")));
            // 设置文件的保存路径
            String filePath = request.getServletContext().getRealPath("/") + "file_upload" + File.separator + "admin_user" + File.separator + fileName;

            // 转存文件
            try {
                userHeadSculpture.transferTo(new File(filePath));
            } catch (IOException e) {
                logger.error(e.getMessage());
                return AppResponse.fail("文件上传失败！");
            }

            return AppResponse.success("上传成功！", File.separator + "file_upload" + File.separator + "admin_user" + File.separator + fileName);
        }

        logger.error("文件不存在！");
        return AppResponse.fail("文件上传失败！");
    }

    /**
     * 用户修改信息、激活
     *
     * @param data    表单数据
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("personal.do")
    @ResponseBody
    public AppResponse<AdminUser> personal(String data, HttpSession session) {
        // 前台字符串数据转化为Map
        Gson gson = new Gson();
        AdminUser adminUser = gson.fromJson(data, new TypeToken<AdminUser>() {
        }.getType());

        // 激活用户
        if (StringUtils.equals(adminUser.getUserStatus(), "1")) {
            adminUser.setUserStatus("2");
        }

        // 更新操作信息
        adminUser.setUpdateUserNo("1l");
        adminUser.setUpdateTime(new Date());

        // 更新数据
        boolean rel = adminUserService.updateById(adminUser);
        if (rel) {
            return AppResponse.success("信息更新成功！");
        }

        // 操作做失败
        return AppResponse.fail("用户编辑失败，请刷新页面后重新提交！");
    }

    /**
     * 检查用户信息是否合法
     *
     * @param userNo 用户编号
     * @param field  字段
     * @param value  值
     * @return 返回检查结果 true-可用  false-不可用
     */
    @RequestMapping("check_user.do")
    @ResponseBody
    public AppResponse<String> checkUser(String userNo, String field, String value) {
        // 查询出 userNo 不是当前记录的但是字段值相同的所有的记录
        QueryWrapper<AdminUser> adminUserQueryWrapper = new QueryWrapper<>();
        adminUserQueryWrapper.ne("user_no", userNo).eq(field, value);
        List<AdminUser> adminUserList = adminUserService.list(adminUserQueryWrapper);

        // 名称可用
        if (adminUserList.isEmpty()) {
            return AppResponse.success("此字段值可用！", "true");
        }

        return AppResponse.success("此字段值不可用！", "false");
    }
}
