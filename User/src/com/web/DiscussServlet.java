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

		  //��ȡaction
				String action = request.getParameter("action");
			
				  //�����ȡ����
				 try {
					Method method =  this.getClass().getDeclaredMethod(action, HttpServletRequest.class,HttpServletResponse.class);
					method.invoke(this, request,response);//ִ�д���������
				}  catch (Exception e) {
					e.printStackTrace();
				}
				 
	
				 
			}

	public void doDeleteDiscuss(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//������ʾ����
			response.setContentType("text/html;charset=utf-8");
			Discuss discuss  = new Discuss();
			
			    String d = request.getParameter("id");
			    int id = Integer.parseInt(d);
			    discuss.setId(id);
			    //ʵ�ֽӿڴ�������
			    DiscussService discussService = new DiscussServiceImpl();
			    discussService.doDelete(discuss);
			    PrintWriter  out = response.getWriter();
			       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
			       out.print("<script>");
			       out.print("alert('ɾ�����۳ɹ�');");
			       out.print("window.location.href='showDital.jsp'");
			       out.print("</script>");
			       out.close();
	}




	public void doPostDiscuss(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		//������ʾ����
			response.setContentType("text/html;charset=utf-8");
			//��ȡsessionֵ
			HttpSession session = request.getSession();
			String name = (String)session.getAttribute("username");
			
			//��ȡno
			Integer o = (Integer)session.getAttribute("no");
			int no = o.intValue();
			//��ȡ����Ϣ
			
			String content = request.getParameter("content");
			Discuss discuss = new Discuss();
			discuss.setName(name);
			discuss.setContent(content);
			
			//��������
			DiscussService discussService = new DiscussServiceImpl();
			
			if(name!=null){
			discussService.postDiscuss(discuss,no);
			
			session.setAttribute("no", no);
		   PrintWriter  out = response.getWriter();
		   out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
		   out.print("<script>");
		   out.print("alert('���۳ɹ�');");
		   out.print("window.location.href='showDital.jsp'");
		   out.print("</script>");
		   out.close();
			}else{
				   PrintWriter  out = response.getWriter();
			       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
			       out.print("<script>");
			       out.print("alert('��û��Ȩ�޷������뷵��ע���Ա');");
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
