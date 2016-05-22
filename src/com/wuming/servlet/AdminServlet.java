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
@WebServlet("/AdminServlet")
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
		String status=request.getParameter("status");
		if(status.equals("login")){
			String aname=request.getParameter("aname");
			String apass=request.getParameter("apass");
			if(aname.equals(apass)){
				request.getSession().setAttribute("aname", aname);
				//判断用户是否需要保存自己的登录信息
				Cookie anameCookie=new Cookie("aname",aname);
				Cookie apassCookie=new Cookie("apass",apass);
				anameCookie.setMaxAge(60*60*24*Integer.parseInt(request.getParameter("auto")));
				apassCookie.setMaxAge(anameCookie.getMaxAge());
				//response代表响应
				response.addCookie(anameCookie);
				response.addCookie(apassCookie);
				response.sendRedirect("/Jsp/admin/adminIndex.jsp");
			}else{
				request.setAttribute("error", "登录失败");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		}else if(status.equals("auto")){
			Cookie[] cookies=request.getCookies();
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("aname")){
					request.setAttribute("aname", cookie.getValue());
				}
				if(cookie.getName().equals("apass")){
					request.setAttribute("apass", cookie.getValue());
				}
			}
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

}
