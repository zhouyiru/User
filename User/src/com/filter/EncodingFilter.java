package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
public class EncodingFilter implements Filter {

	@Override
	public void destroy() {

	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {		

	}
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
    //ǿ��ת��
	HttpServletRequest request = (HttpServletRequest)req;
	
	/**
	 * post�ύ����
	 */
	request.setCharacterEncoding("utf-8");

		chain.doFilter(req , resp );
	}
}


