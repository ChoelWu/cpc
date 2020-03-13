package com.joe.controller.index;

// +----------------------------------------------------------------------
// | Created by IntelliJ IDEA.
// +----------------------------------------------------------------------
// | Date: 2020/3/13
// +----------------------------------------------------------------------
// | Author: Joe
// +----------------------------------------------------------------------
// | Description: 用户端用户信息
// +----------------------------------------------------------------------

import com.joe.entity.IndexUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/index/user")
public class UserController {
    /**
     * 用户中心
     *
     * @param session session
     * @param model   model
     * @return 返回页面视图
     */
    @RequestMapping("/index.do")
    public String view(HttpSession session, Model model) {
        IndexUser indexUser = (IndexUser) session.getAttribute("indexUser");

        model.addAttribute("indexUser", indexUser);

        return "Index/User/index";
    }
}
