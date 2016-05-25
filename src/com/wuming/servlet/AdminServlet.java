package com.wuming.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String status = request.getParameter("status");

        if ("login".equals(status)) {
            String aname = request.getParameter("aname");
            String apass = request.getParameter("apass");
            if (aname.equals(apass)) {
                request.getSession().setAttribute("aname", aname);
                //判断用户是否需要保存自己的登录信息
                Cookie anameCookie = new Cookie("aname", aname);
                Cookie apassCookie = new Cookie("apass", apass);
                anameCookie.setMaxAge(60 * 60 * 24 * Integer.parseInt(request.getParameter("auto")));
                apassCookie.setMaxAge(anameCookie.getMaxAge());
                //response代表响应
                response.addCookie(anameCookie);
                response.addCookie(apassCookie);
                request.getRequestDispatcher("/template/admin/index.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "登录失败");
            }
        } else if ("auto".equals(status)) {
            Cookie[] cookies = request.getCookies();
            String name = null;
            for (Cookie cookie : cookies) {
                if ("aname".equals(cookie.getName())) {
                    name = cookie.getName();
                    request.setAttribute("aname", cookie.getValue());
                }
                if ("apass".equals(cookie.getName())) {
                    request.setAttribute("apass", cookie.getValue());
                }
            }
            // 要使用域名才可以记录 cookies(localhost 或者127.0.0.0 或者192.168.x.x 无法正常使用 cookies)
            if (name != null) {
                request.getSession().setAttribute("aname", name);
                request.getRequestDispatcher("/template/admin/index.jsp").forward(request, response);
            }
        }
        request.getRequestDispatcher("/template/student/login.jsp").forward(request, response);
    }

}
