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
       //������ʾ�����ʽ
		response.setContentType("text/html;charset=utf-8");			
				//��ȡ�û����������֤��
				String code = request.getParameter("code");				
				//��ȡϵͳ���ɵ���֤��
				//��session���ȡϵͳ���ɵ���֤��
				HttpSession session = request.getSession(true);						
		   String username = request.getParameter("username");
		   String userpwd = request.getParameter("userpwd");
		   
		 //��������ʵ�ֽӿ�
		   UserTable usertable = new UserTable();
		   UserTableService userService = new UserTableServiceImpl();
		   //ע��ֵ��bean��
		   usertable.setUsername(username);
		   usertable.setUserpwd(userpwd);
  if(session!=null){
			String sCode = (String)session.getAttribute("sCode");
			//�Ա�
			if(!code.trim().equals(sCode.trim())){
				   PrintWriter  out = response.getWriter();
			       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
			       out.print("<script>");
			       out.print("alert('���������֤������������');");
			       out.print("window.location.href='Login.jsp'");
			       out.print("</script>");
			       out.close();					
	        }else{			
		  //��鼤��״̬
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
							       out.print("alert('��¼ʧ�ܣ��û�δ����');");
							       out.print("window.location.href='Login.jsp'");
							       out.print("</script>");
								   out.close();		  
						           }				
		           else{					     				 
			              if(userService.isLogin(usertable)){
				   //��ѯͷ��									  							 							   
								   rs = userService.findUserByUsername(username);
								   String img = null;
								   while(rs.next()) {
									   img = rs.getString("userimg");
									   System.out.println(img);
								   }
							   
						   //����sessionֵ		   
						   session.setAttribute("username",username);
						   session.setAttribute("userimg", img);	
						   session.setAttribute("ip", request.getRemoteHost());
						   response.sendRedirect("MyJsp.jsp");
						        }else{
							   PrintWriter  out = response.getWriter();
						       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
						       out.print("<script>");
						       out.print("alert('��¼ʧ�ܣ��û������������');");
						       out.print("window.location.href='Login.jsp'");
						       out.print("</script>");
						       out.close();
			                    }//��½����
			             }//״̬
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			  }//�п�
    	    }//��֤��
    	}//session
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
doGet(request, response);
	}
	
	

}
