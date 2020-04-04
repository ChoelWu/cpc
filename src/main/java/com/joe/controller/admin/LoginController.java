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
import com.joe.entity.AdminAuth;
import com.joe.entity.AdminRole;
import com.joe.entity.AdminUser;
import com.joe.service.system.AdminAuthService;
import com.joe.service.system.AdminRoleService;
import com.joe.service.system.AdminUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/login")
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private AdminRoleService adminRoleService;

    @Resource
    private AdminAuthService adminAuthService;

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
     * @param data    前端传入的对象
     * @param session session
     * @return 返回登录结果
     */
    @RequestMapping("/login.do")
    @ResponseBody
    public AppResponse<String> login(String data, HttpSession session) {
        // 将表单数据转化为对象
        Gson gson = new Gson();
        AdminUser adminUser = gson.fromJson(data, new TypeToken<AdminUser>() {
        }.getType());

        // 查询用户
        QueryWrapper<AdminUser> adminUserQueryWrapper = new QueryWrapper<>();
        adminUserQueryWrapper.eq("user_account", adminUser.getUserAccount());
        AdminUser existAdminUser = adminUserService.getOne(adminUserQueryWrapper);

        // 加密
        String userNo = (null == existAdminUser || StringUtils.isBlank(existAdminUser.getUserNo())) ? "" : existAdminUser.getUserNo();
        String password = CommonFunctions.encryptPassword(adminUser.getUserPassword(), userNo);

        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(adminUser.getUserAccount(), password);

        // 登录
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);

            // 根据角色编号查询角色，获取权限
            QueryWrapper<AdminRole> adminRoleQueryWrapper = new QueryWrapper<>();
            adminRoleQueryWrapper.eq("role_no", existAdminUser.getRoleNo());
            AdminRole adminRole = adminRoleService.getOne(adminRoleQueryWrapper);

            // 查询用户的权限信息
            List<AdminAuth> adminAuthList;
            if (1 == adminRole.getRoleId()) {
                // 非超级管理员
                String[] permissionArray = adminRole.getRoleAuth().split(",");
                QueryWrapper<AdminAuth> adminAuthQueryWrapper = new QueryWrapper<>();
                adminAuthQueryWrapper.in("auth_no", permissionArray);
                adminAuthList = adminAuthService.list(adminAuthQueryWrapper);
            } else {
                // 超级管理员拥有所有权限
                adminAuthList = adminAuthService.list();
            }

            // 验证通过，生成 session 信息
            session.setAttribute("adminUser", existAdminUser);
            session.setAttribute("adminAuthList", adminAuthList);

            // 登陆成功
            logger.info("账户为 " + adminUser.getUserAccount() + " 的后端用户登陆成功");
            return AppResponse.success("登录成功！");
        } catch (UnknownAccountException ex) {
            // 登陆失败
            logger.info("账户为 " + adminUser.getUserAccount() + " 的后端用户登陆失败，账号不存在！");
            return AppResponse.fail("帐号不存在，请检查帐号是否正确！");
        } catch (IncorrectCredentialsException ex) {
            // 登陆失败
            logger.info("账户为 " + adminUser.getUserAccount() + " 的后端用户登陆失败，密码不正确！");
            return AppResponse.fail("密码错误，请检查后重新登录！");
        } catch (AuthenticationException ex) {
            // 登陆失败
            logger.info("账户为 " + adminUser.getUserAccount() + " 的后端用户登陆失败，验证失败！");
            return AppResponse.fail("登录失败！");
        } catch (Exception e) {
            // 登陆失败
            logger.info("账户为 " + adminUser.getUserAccount() + " 的后端用户登陆失败，内部异常！");
            return AppResponse.fail("登录失败！");
        }
    }

    /**
     * 退出登录
     *
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("/logout.do")
    @ResponseBody
    public AppResponse<String> logout(HttpSession session) {
        // 销毁 session 信息
        AdminUser adminUser = (AdminUser) session.getAttribute("adminUser");
        session.removeAttribute("adminUser");

        Subject subject = SecurityUtils.getSubject();

        if (subject != null) {
            try {
                subject.logout();

                // 返回操作结果
                logger.info("用户名为 " + adminUser.getUserAccount() + " 的后端用户退出登录！");
                return AppResponse.success("用户退出登录！");
            } catch (Exception ex) {
                // 退出失败
                logger.info("账户为 " + adminUser.getUserAccount() + " 的后端用户登陆失败，内部异常！");
                return AppResponse.fail("退出登录失败！");
            }
        }

        return AppResponse.fail("退出登录失败！");
    }
}
