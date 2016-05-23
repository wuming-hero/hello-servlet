package com.wuming.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AdminFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filter) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        System.out.print("qwe=" + session.getAttribute("aname"));
        if (session.getAttribute("aname") != ("")) {
            System.out.print("--正常访问--");
            //跳转到下一个过滤器,如果没有则跳转到目标页面
            // pass the request along the filter chainqw
            filter.doFilter(request, response);
        } else {
            System.out.print("--非法进入--");
            request.setAttribute("error", "非法访问");
            request.getRequestDispatcher("/template/student/login.jsp").forward(request, response);
        }

    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        System.out.print("--adminFilter启动--");

    }

}
