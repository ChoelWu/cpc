package com.joe.commons.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 密码检查过滤器，检查当前登录用户的密码是否符合约定的规则，如果不符合则跳转至修改密码页面
 */
public class IndexInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
//        SessionContainer sc = (SessionContainer) request.getSession().getAttribute("sc");
//        if (sc == null) {
//            // 跳转登录请求
//            request.getRequestDispatcher("/admin/login/login_page.do").forward(request, response);
//            return false;
//        }
//        Validate valid = Validate.getInstance();
//        if (!valid.validPassword(sc.getSourcePassword())) {
//            // 跳转至修改密码请求
//            request.getRequestDispatcher("/system/sysUser/goModifyInfoPage?userId=" + sc.getUserId() + "&flag=password&tipFlag=true").forward(request, response);
//            return false;
//        }

        return true;
    }
}
