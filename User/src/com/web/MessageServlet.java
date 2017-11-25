package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.entity.Message;
import com.service.MessageService;
import com.service.impl.MessageServiceImpl;

public class MessageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
       //获取action
		String action = request.getParameter("action");
	
		  //反射获取方法
		 try {
			Method method =  this.getClass().getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doDeleteMessage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//设置显示编码
			response.setContentType("text/html;charset=utf-8");
			 Message message = new Message();
			    String no = request.getParameter("no");
			    int id = Integer.parseInt(no);
			    message.setNo(id);
			    //实现接口创建对象
			    MessageService messageService = new MessageServiceImpl();
			    
			    messageService.doDelete(message);
			    PrintWriter  out = response.getWriter();
			       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
			       out.print("<script>");
			       out.print("alert('删除成功');");
			       out.print("window.location.href='showMessage.jsp'");
			       out.print("</script>");
			       out.close();
	}

	public void doUpdateMessage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html;charset=utf-8");
		  
		  String content = request.getParameter("content");
		  String id = request.getParameter("no");
		  int no = Integer.parseInt(id);
		    
		
        Message message = new Message();
        message.setNo(no);
        message.setContent(content);
        
        MessageService messageService = new MessageServiceImpl();
       
        messageService.doUpdate(message);
        PrintWriter  out = response.getWriter();
        out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
        out.print("<script>");
        out.print("alert('修改成功');");
        out.print("window.location.href='showMessage.jsp'");
        out.print("</script>");
        out.close();
	}

	public void doPostMessage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//设置显示编码
		response.setContentType("text/html;charset=utf-8");
		//获取session值
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("username");
		//获取表单信息
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Message message = new Message();
		message.setName(name);
		message.setTitle(title);
		message.setContent(content);
		
		//创建对象
		MessageService messageService = new MessageServiceImpl();
		
		if(name!=null){
		messageService.postMessage(message);
	   PrintWriter  out = response.getWriter();
       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
       out.print("<script>");
       out.print("alert('发布成功');");
       out.print("window.location.href='showMessage.jsp'");
       out.print("</script>");
       out.close();
		}else{
			   PrintWriter  out = response.getWriter();
		       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
		       out.print("<script>");
		       out.print("alert('您没有权限发布，请返回注册会员');");
		       out.print("window.location.href='showMessage.jsp'");
		       out.print("</script>");
		       out.close();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

doGet(request, response);
	}

}
