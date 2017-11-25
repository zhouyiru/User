package com.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class SecurityFilter implements Filter{

	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String ip  = request.getRemoteHost();
		response.setContentType("text/html;charset=utf-8");
		
		if("localhost".equals(ip)|| "127.0.0.1".equals(ip)){
			//1)管理员
			chain.doFilter(request, response);
		}else{
			//2)非管理员
			   PrintWriter  out = response.getWriter();
		       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
		       out.print("<script>");
		       out.print("alert('你没有权限踢出用户！');");
		       out.print("</script>");
		       out.close();
		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}

