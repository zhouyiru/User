package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.entity.UserTable;
import com.service.UserTableService;
import com.service.impl.UserTableServiceImpl;

@SuppressWarnings("serial")
public class doUpdateServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           //设置显示编码
		   response.setContentType("text/html;charset=utf-8");
		   //创建对象，实现接口
		   UserTable usertable = new UserTable(); 
		 
		   UserTableService userService = new UserTableServiceImpl();
		   //接收form传过来的值
		   String id = request.getParameter("userid");
		   int userid = Integer.parseInt(id);
		   String username = request.getParameter("username");
		    String userpwd = request.getParameter("userpwd");
		    String age = request.getParameter("userage");
		    int userage = Integer.parseInt(age);
		    String usergender = request.getParameter("usergender");
		    String useredu = request.getParameter("useredu");
		    String phone = request.getParameter("userphone");
		    long userphone = Long.parseLong(phone);
		    String useraddress= request.getParameter("useraddress");
		    //将值封装到bean中
		    usertable.setUserid(userid);
	        usertable.setUsername(username);
	        usertable.setUserpwd(userpwd);
	        usertable.setUserage(userage);
	        usertable.setUsergender(usergender);
	        usertable.setUseredu(useredu);
	        usertable.setUserphone(userphone);
	        usertable.setUseraddress(useraddress);
		   
		   //userService调用修改方法
		   userService.doUpdate(usertable);
		  
		   //显示js功能页面
		   PrintWriter  out = response.getWriter();
	       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
	       out.print("<script>");
	       out.print("alert('修改成功');");
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
