package com.joe.controller.index;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2020/1/2
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 登录
// +----------------------------------------------------------------------

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.joe.commons.app.AppResponse;
import com.joe.commons.app.CommonFunctions;
import com.joe.controller.admin.LoginController;
import com.joe.entity.AdminUser;
import com.joe.entity.IndexUser;
import com.joe.service.system.IndexUserService;
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
@RequestMapping("/index/login")
public class IndexLoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private IndexUserService indexUserService;

    /**
     * 登录页面
     *
     * @return 返回页面视图
     */
    @RequestMapping("/login_page.do")
    public String loginPage() {
        return "Index/Login/index";
    }

    /**
     * 登录
     *
     * @param data    用户登录信息
     * @param session session
     * @return 返回登录结果
     */
    @RequestMapping("/login.do")
    @ResponseBody
    public AppResponse<String> login(String data, HttpSession session) {
        // 将表单数据转化为对象
        Gson gson = new Gson();
        AdminUser user = gson.fromJson(data, new TypeToken<AdminUser>() {
        }.getType());

        // 查询用户
        QueryWrapper<IndexUser> indexUserQueryWrapper = new QueryWrapper<>();
        indexUserQueryWrapper.eq("user_account", user.getUserAccount());
        IndexUser indexUser = indexUserService.getOne(indexUserQueryWrapper);

        // 用户不存在
        if (null == indexUser) {
            logger.error("用户登录账号为 " + user.getUserAccount() + " 的用户端用户账户不存在，登录失败");
            return AppResponse.fail("此用户不存在，请检查帐号是否正确！");
        }

        // 生成加密后的密码
        String password = CommonFunctions.encryptPassword(user.getUserPassword(), indexUser.getUserNo());
        // 用户密码不正确
        if (!StringUtils.equals(indexUser.getUserPassword(), password)) {
            logger.error("用户名为 " + indexUser.getUserName() + " 的用户端用户密码不正确，登录失败");
            return AppResponse.fail("密码错误，请检查重新输入！");
        }

        // 验证通过，生成 session 信息
        session.setAttribute("indexUser", indexUser);

        // 登陆成功
        logger.info("用户名为 " + indexUser.getUserName() + " 的用户端用户登陆成功");
        return AppResponse.success("登录成功！");
    }

    /**
     * 登出
     *
     * @param session session
     * @return 返回操作结果
     */
    @RequestMapping("/logout.do")
    @ResponseBody
    public AppResponse<String> logout(HttpSession session) {
        // 销毁 session 信息
        IndexUser indexUser = (IndexUser) session.getAttribute("indexUser");
        session.removeAttribute("indexUser");
        // 返回操作结果
        logger.info("用户名为 " + indexUser.getUserName() + " 的后端用户退出登录！");
        return AppResponse.success("用户退出登录！");
    }

    /**
     * 完善用户信息页面
     *
     * @param session session
     * @param model   model
     * @return 返回页面信息
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
