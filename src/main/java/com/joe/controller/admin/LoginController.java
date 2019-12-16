package com.joe.controller.admin;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2019/11/14
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 登录管理
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.entity.AdminUser;
import com.joe.service.system.AdminUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/login")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private AdminUserService adminUserService;

    /**
     * 显示登录页
     *
     * @return 返回页面视图
     */
    @RequestMapping("/login_page.do")
    public String login_page() {
        return "Admin/Login/index";
    }

    /**
     * 登录
     *
     * @param data    前台表单数据
     * @param session session
     * @return 返回登录结果
     */
    @RequestMapping("/login.do")
    @ResponseBody
    public AppResponse login(String data, HttpSession session) {
        // 将表单数据转化为对象
        Gson gson = new Gson();
        AdminUser user = gson.fromJson(data, new TypeToken<AdminUser>() {
        }.getType());

        // 查询用户
        QueryWrapper<AdminUser> adminUserQueryWrapper = new QueryWrapper<>();
        adminUserQueryWrapper.eq("user_account", user.getUserAccount());
        AdminUser adminUser = adminUserService.getOne(adminUserQueryWrapper);

        // 用户不存在
        if (null == adminUser) {
            logger.error("用户登录账号为 " + user.getUserAccount() + " 的后端用户账户不存在，登录失败");
            return AppResponse.fail("此用户不存在，请检查帐号是否正确！");
        }

        // 生成加密后的密码
        String password = CommonFunctions.encryptPassword(user.getUserPassword(), adminUser.getUserNo());
        // 用户密码不正确
        if (!StringUtils.equals(adminUser.getUserPassword(), password)) {
            logger.error("用户名为 " + adminUser.getUserName() + " 的后端用户密码不正确，登录失败");
            return AppResponse.fail("密码错误，请检查重新输入！");
        }

        // 验证通过，生成 session 信息
        session.setAttribute("adminUser", adminUser);

        // 登陆成功
        logger.info("用户名为 " + adminUser.getUserName() + " 的后端用户登陆成功");
        return AppResponse.success("登录成功！");
    }

    /**
     * 退出登录
     *
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("/logout.do")
    @ResponseBody
    public AppResponse logout(HttpSession session) {
        // 销毁 session 信息
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
        session.removeAttribute("adminUser");
        // 返回操作结果
        logger.info("用户名为 " + adminUser.getUserName() + " 的后端用户退出登录！");
        return AppResponse.success("用户退出登录！");
    }

    /**
     * 完善用户信息
     *
     * @param session session
     * @param model   model
     * @return 返回页面视图
     */
    @RequestMapping("/improve_info")
    public String improveInfo(HttpSession session, Model model) {
        // 获取用户session信息
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");

        // 绑定用户信息到前台
        model.addAttribute("adminUser", adminUser);

        return "/Admin/Login/improveInfo";
    }
}
