package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.entity.UserTable;
import com.service.UserTableService;
import com.service.impl.UserTableServiceImpl;

@SuppressWarnings("serial")
public class doLoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       //设置显示编码格式
		response.setContentType("text/html;charset=utf-8");			
				//获取用户的输入的验证码
				String code = request.getParameter("code");				
				//获取系统生成的验证码
				//从session域获取系统生成的验证码
				HttpSession session = request.getSession(true);						
		   String username = request.getParameter("username");
		   String userpwd = request.getParameter("userpwd");
		   
		 //创建对象并实现接口
		   UserTable usertable = new UserTable();
		   UserTableService userService = new UserTableServiceImpl();
		   //注入值到bean中
		   usertable.setUsername(username);
		   usertable.setUserpwd(userpwd);
  if(session!=null){
			String sCode = (String)session.getAttribute("sCode");
			//对比
			if(!code.trim().equals(sCode.trim())){
				   PrintWriter  out = response.getWriter();
			       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
			       out.print("<script>");
			       out.print("alert('您输入的验证码有误，请重试');");
			       out.print("window.location.href='Login.jsp'");
			       out.print("</script>");
			       out.close();					
	        }else{			
		  //检查激活状态
               if(username!=null){	 
	            ResultSet rs = null;
			  rs = userService.findUserByUsername(username);
			  int status;
			  try {
				while(rs.next()){
						status = rs.getInt("status");
						 if(status==0){
								   PrintWriter  out = response.getWriter();
							       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
							       out.print("<script>");
							       out.print("alert('登录失败，用户未激活');");
							       out.print("window.location.href='Login.jsp'");
							       out.print("</script>");
								   out.close();		  
						           }				
		           else{					     				 
			              if(userService.isLogin(usertable)){
				   //查询头像									  							 							   
								   rs = userService.findUserByUsername(username);
								   String img = null;
								   while(rs.next()) {
									   img = rs.getString("userimg");
									   System.out.println(img);
								   }
							   
						   //设置session值		   
						   session.setAttribute("username",username);
						   session.setAttribute("userimg", img);	
						   session.setAttribute("ip", request.getRemoteHost());
						   response.sendRedirect("MyJsp.jsp");
						        }else{
							   PrintWriter  out = response.getWriter();
						       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
						       out.print("<script>");
						       out.print("alert('登录失败，用户名或密码错误');");
						       out.print("window.location.href='Login.jsp'");
						       out.print("</script>");
						       out.close();
			                    }//登陆密码
			             }//状态
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			  }//判空
    	    }//验证码
    	}//session
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
doGet(request, response);
	}
	
	

}
