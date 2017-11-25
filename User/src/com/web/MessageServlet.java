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
       //��ȡaction
		String action = request.getParameter("action");
	
		  //�����ȡ����
		 try {
			Method method =  this.getClass().getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this, request,response);
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doDeleteMessage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//������ʾ����
			response.setContentType("text/html;charset=utf-8");
			 Message message = new Message();
			    String no = request.getParameter("no");
			    int id = Integer.parseInt(no);
			    message.setNo(id);
			    //ʵ�ֽӿڴ�������
			    MessageService messageService = new MessageServiceImpl();
			    
			    messageService.doDelete(message);
			    PrintWriter  out = response.getWriter();
			       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
			       out.print("<script>");
			       out.print("alert('ɾ���ɹ�');");
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
        out.print("alert('�޸ĳɹ�');");
        out.print("window.location.href='showMessage.jsp'");
        out.print("</script>");
        out.close();
	}

	public void doPostMessage(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//������ʾ����
		response.setContentType("text/html;charset=utf-8");
		//��ȡsessionֵ
		HttpSession session = request.getSession();
		String name = (String)session.getAttribute("username");
		//��ȡ����Ϣ
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Message message = new Message();
		message.setName(name);
		message.setTitle(title);
		message.setContent(content);
		
		//��������
		MessageService messageService = new MessageServiceImpl();
		
		if(name!=null){
		messageService.postMessage(message);
	   PrintWriter  out = response.getWriter();
       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
       out.print("<script>");
       out.print("alert('�����ɹ�');");
       out.print("window.location.href='showMessage.jsp'");
       out.print("</script>");
       out.close();
		}else{
			   PrintWriter  out = response.getWriter();
		       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
		       out.print("<script>");
		       out.print("alert('��û��Ȩ�޷������뷵��ע���Ա');");
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
