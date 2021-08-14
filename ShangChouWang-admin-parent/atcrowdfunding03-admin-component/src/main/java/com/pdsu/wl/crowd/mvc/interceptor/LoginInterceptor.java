package com.pdsu.wl.crowd.mvc.interceptor;

import com.pdsu.wl.crowd.Entity.Admin;
import com.pdsu.wl.crowd.constant.CrowdConstant;
import com.pdsu.wl.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author wl
 * @Date 2021/7/23 21:11
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 1.通过request对象获取Session对象
        HttpSession session = request.getSession();

        // 2.尝试从Session域中获取Admin对象
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN);

        // 3.判断admin对象是否为空,为空表示没有登录
        if (admin == null) {

            response.sendRedirect("/atcrowdfunding02_admin_webui_war_exploded/admin/to/login/page.html");
            // 4.抛出异常
            throw new AccessForbiddenException(CrowdConstant.MESSAGE_ACCESS_FORBIDEN);
        }

        // 5.如果Admin对象不为null,则返回true就行
        return true;
    }

}
