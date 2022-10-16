package com.cybersoft.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class CustomFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if (session.getAttribute("isLogin") != null && !session.getAttribute("isLogin").equals("")) {
            //đã login
            boolean isLogin = (boolean) session.getAttribute("isLogin");
            if (isLogin) {
                if (request.getServletPath().equals("/login")) {
                    //nếu là trang login -> chuyển về home
                    response.sendRedirect(request.getContextPath() + "/home");
                } else {
                    //cho phép truy cập
                    filterChain.doFilter(request, response);
                }
            } else {
                //chuyển về trang login
                response.sendRedirect(request.getContextPath() + "/login");
            }
        } else {
            //chưa login
            //chuyển về page login
            if (request.getServletPath().equals("/login")) {
                filterChain.doFilter(request, response);
            } else {
                response.sendRedirect(request.getContextPath() + "/login");
            }
        }
    }
}
