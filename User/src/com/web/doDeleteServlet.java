package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.service.UserTableService;
import com.service.impl.UserTableServiceImpl;

@SuppressWarnings("serial")
public class doDeleteServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
          //设置显示的编码
		  response.setContentType("text/html;charset=utf-8");
		   //实现接口创建对象
		   UserTableService userService = new UserTableServiceImpl();
		   //接收form表单传来的id值
		   String id=request.getParameter("userid");
		   int userid = Integer.parseInt(id);
		   
		   //调用service删除方法
		   userService.doDelete(userid);
		   PrintWriter  out = response.getWriter();
	       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
	       out.print("<script>");
	       out.print("alert('删除成功');");
	       out.print("window.location.href='MyFindJsp.jsp'");
	       out.print("</script>");
	       out.close();
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		doGet(request, response);

	}

}
