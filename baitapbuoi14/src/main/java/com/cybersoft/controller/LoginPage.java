package com.cybersoft.controller;


import com.cybersoft.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginPage", urlPatterns = {"/login"})
public class LoginPage extends HttpServlet {

    private LoginService loginService = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        boolean isLogin = false;
        isLogin = loginService.checkLogin(email,password);



        if (isLogin){
            HttpSession session = req.getSession();
            session.setAttribute("isLogin",true);
            session.setMaxInactiveInterval(5*60);
            resp.sendRedirect(req.getContextPath()+"/home");
        } else {
            req.setAttribute("error","Wrong Username or Password");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}