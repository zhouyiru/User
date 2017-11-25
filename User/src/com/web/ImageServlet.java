package com.web;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.ValidateCodeUtil;

public class ImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		//生成一张随机验证码图片，并写出到浏览器
		OutputStream out = response.getOutputStream();
		String sCode = ValidateCodeUtil.genNewCode(out);
		//把sCode共享给用户登录时使用
		session.setAttribute("sCode", sCode);
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


}
