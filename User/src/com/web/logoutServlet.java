package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class logoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       //������ʾ�����ʽ
		response.setContentType("text/html;charset=utf-8");
		//��ȡsession
		HttpSession session = request.getSession(false);
		//�ֶ�sessionʧЧ
		session.removeAttribute("username");
		session.removeAttribute("ip");
	    
		 PrintWriter  out = response.getWriter();
	       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
	       out.print("<script>");
	       out.print("window.top.location='Login.jsp'");//������ܣ��ص���ԭʼҳ��
	       out.print("</script>");
	       out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);

	}

}
