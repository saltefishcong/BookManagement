package com.example.bookmanagement.Filter;

import com.example.bookmanagement.eity.Administrator;
import com.example.bookmanagement.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@WebFilter
public class AdministratorFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("管理员拦截器开始： " + new Date());
    }

    @Autowired
    private AdministratorService service;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        if (request.getParameter("identification") == null || request.getParameter("identification").equals("")
                || request.getParameter("password") == null || request.getParameter("password").equals("")
                || request.getParameter("name") == null || request.getParameter("name").equals("")) {
            return;
        }
        try {
            if (service.loginAdministrator(new Administrator(request.getParameter("name")
                    , request.getParameter("password")
                    , request.getParameter("identification"))) != null) {
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            System.out.println("没有权限");
        }
    }

    @Override
    public void destroy() {
        System.out.println("管理员拦截器结束： " + new Date());
    }
}
