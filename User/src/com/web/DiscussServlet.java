package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.entity.Discuss;
import com.service.DiscussService;
import com.service.impl.DiscussServiceImpl;

public class DiscussServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		  //获取action
				String action = request.getParameter("action");
			
				  //反射获取方法
				 try {
					Method method =  this.getClass().getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);
					method.invoke(this, request,response);//执行带参数方法
				}  catch (Exception e) {
					e.printStackTrace();
				}
				 
	
				 
			}

	public void doDeleteDiscuss(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//设置显示编码
			response.setContentType("text/html;charset=utf-8");
			Discuss discuss  = new Discuss();
			
			    String d = request.getParameter("id");
			    int id = Integer.parseInt(d);
			    discuss.setId(id);
			    //实现接口创建对象
			    DiscussService discussService = new DiscussServiceImpl();
			    discussService.doDelete(discuss);
			    PrintWriter  out = response.getWriter();
			       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
			       out.print("<script>");
			       out.print("alert('删除评论成功');");
			       out.print("window.location.href='showDital.jsp'");
			       out.print("</script>");
			       out.close();
	}




	public void doPostDiscuss(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//设置显示编码
			response.setContentType("text/html;charset=utf-8");
			//获取session值
			HttpSession session = request.getSession();
			String name = (String)session.getAttribute("username");
			
			//获取no
			Integer o = (Integer)session.getAttribute("no");
			int no = o.intValue();
			//获取表单信息
			
			String content = request.getParameter("content");
			Discuss discuss = new Discuss();
			discuss.setName(name);
			discuss.setContent(content);
			
			//创建对象
			DiscussService discussService = new DiscussServiceImpl();
			
			if(name!=null){
			discussService.postDiscuss(discuss,no);
			
			session.setAttribute("no", no);
		   PrintWriter  out = response.getWriter();
		   out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
		   out.print("<script>");
		   out.print("alert('评论成功');");
		   out.print("window.location.href='showDital.jsp'");
		   out.print("</script>");
		   out.close();
			}else{
				   PrintWriter  out = response.getWriter();
			       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
			       out.print("<script>");
			       out.print("alert('您没有权限发布，请返回注册会员');");
			       out.print("window.location.href='showDital.jsp'");
			       out.print("</script>");
			       out.close();
			}
	}




	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doGet(request, response);

	}

}
