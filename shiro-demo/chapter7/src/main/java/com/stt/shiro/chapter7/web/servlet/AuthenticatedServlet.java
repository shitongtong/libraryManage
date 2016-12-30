package com.stt.shiro.chapter7.web.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 展示当前登录的用户
 *
 * Created by Administrator on 2016-12-29.
 *
 * @author shitongtong
 */
@WebServlet(name = "authenticatedServlet",urlPatterns = "/authenticated")
public class AuthenticatedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()){
            req.getRequestDispatcher("/WEB-INF/jsp/authenticated.jsp").forward(req, resp);
        }else{
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        }
    }
}
