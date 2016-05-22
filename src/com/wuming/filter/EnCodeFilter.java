package com.wuming.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EnCodeFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		//System.out.println("---结束--");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain filter) 
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("--doFilter--");
		request.setCharacterEncoding("UTF-8");											
		response.setCharacterEncoding("UTF-8");
		//如果有下一个过滤器则跳转到下一个过滤器,否则跳转到目标页面
		filter.doFilter(request, response);												
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("---EnCodeFilter开始---");

	}

}
