package com.wuming.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CookieServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		// 当用户第一次登录时显示:您是第一次登录...
		// 假设我们保存上次登录记录的cookie为"lasttime""2012-08-31 22:25:25"
		Cookie[] cookies = request.getCookies();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		String nowTime = simpleDateFormat.format(new Date());
		boolean b = false; // 假设没有lasttime这个cookie
		if (cookies != null) { // 有cookie才去遍历,cookie无法筛选，只有拿出来后再根据信息选择
			for (Cookie cookie : cookies) {
				String name = cookie.getName();
				if ("lasttime".equals(name)) {
					out.println("您上次登录时间是" + cookie.getValue());
					// 更新时间为本次访问的时间点！！
					cookie.setValue(nowTime);
					cookie.setMaxAge(7 * 3600 * 24);
					response.addCookie(cookie);
					b = true;
					break;
				}
			}
		}
		if (b == false) {
			out.println("您是第一次登录...");
			// 创建cookie
			Cookie cookie = new Cookie("lasttime", nowTime);
			cookie.setMaxAge(7 * 3600 * 24); // 设置cookie的生命周期保存一周
			// 把cookie信息写回给浏览器：
			response.addCookie(cookie);
		}
	}

}
