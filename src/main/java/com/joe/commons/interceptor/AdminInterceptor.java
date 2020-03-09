package com.joe.commons.interceptor;

import com.joe.entity.AdminUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 密码检查过滤器，检查当前登录用户的密码是否符合约定的规则，如果不符合则跳转至修改密码页面
 */
public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        AdminUser adminUser = (AdminUser) request.getSession().getAttribute("adminUser");
        // 检查登录
        if (null == adminUser) {
            // 跳转登录请求
            request.getRequestDispatcher("/admin/login/login_page.do").forward(request, response);
            return false;
        }

        // 检查信息是否完善
        if (StringUtils.equals("1", adminUser.getUserStatus())) {
            // 跳转至完善信息页面
            request.getRequestDispatcher("/admin/user/improve_info.do").forward(request, response);
            return false;
        }
        return true;
    }
}
