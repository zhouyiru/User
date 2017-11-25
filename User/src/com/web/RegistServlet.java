package com.web;






import java.io.IOException;
import java.io.PrintWriter;









import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

















import com.bean.entity.UserTable;
import com.service.UserTableService;
import com.service.impl.UserTableServiceImpl;

@SuppressWarnings("serial")
public class RegistServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           //������ʾ����
		   response.setContentType("text/html;charset=utf-8");
		   //��������ʵ�ֽӿ�
		   UserTable usertable = new UserTable(); 
		   UserTableService userService = new UserTableServiceImpl();	
		   //���ձ�ֵ
		   String username = request.getParameter("username");
		    String userpwd = request.getParameter("userpwd");
		    String age = request.getParameter("userage");
		    if(age!=null){
		    int userage = Integer.parseInt(age);
		    usertable.setUserage(userage);}
		    String usergender = request.getParameter("usergender");
		    String useredu = request.getParameter("useredu");
		    String phone = request.getParameter("userphone");
		    if(phone!=null){
		    long userphone = Long.parseLong(phone);
	        usertable.setUserphone(userphone);
            }
		    String email = request.getParameter("email");
		    String useraddress= request.getParameter("useraddress");
		   //��װbean��
	        usertable.setUsername(username);
	        usertable.setUserpwd(userpwd);
	        usertable.setUsergender(usergender);
	        usertable.setUseredu(useredu);
	        usertable.setEmail(email);
	        usertable.setUseraddress(useraddress);
	     
	        usertable.setValidatecode(com.util.WebUtil.uuid());
	  //�п�
	  if(usertable.getUsername()!=null&&usertable.getUserpwd()!=null&&!usertable.getUsername().equals("")){
    	   userService.Regist(usertable);
    	     }
	  
	//�����߳�
			new MySendMailThread(usertable).start();
			
       PrintWriter  out = response.getWriter();
       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
       out.print("<script>");
       out.print("alert('ע��ɹ�,��ȥ���伤���û�');");
       out.print("window.location.href='Regist.jsp'");
       out.print("</script>");
       out.close();
    	 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		doGet(request, response);

	}
	/**
	 * ʹ���̷߳����ʼ�
	 */
class MySendMailThread extends Thread{
		
		private UserTable usertable;
		
		public MySendMailThread(UserTable usertable){
			this.usertable = usertable;
		}
		
		@Override
		public void run() {
			try {
				//1)����һ��Session����,���Ӻ͵�¼������
				/**
				 * ����һ�� �������ӵ����á�
				 * �������� ���ض��û���������base64���ܵĶ���
				 */
				Properties props = new Properties();
				//1.1���ӵķ��ʼ��ķ�������ַ
				props.setProperty("mail.host", "smtp.163.com");
				//1.2 ָ��������֤��¼
				props.setProperty("mail.smtp.auth", "true");
				
				Session  session = Session.getDefaultInstance(props, new Authenticator() {
							@Override
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication("zyr40318076@163.com","zyr40318076");
							}
				});
				//�򿪵���
				session.setDebug(true);
				
				//2)�ڱ��������ϣ� ����һ���ʼ�
				MimeMessage mail = new MimeMessage(session);
				
				//3�������ʼ�����
				///3.1 ���÷�����
				mail.setFrom(new InternetAddress("zyr40318076@163.com"));
				
				//3.2 �����ռ���
				/**
				 * ����һ�� ���ͷ���
				 * 		���ͣ� TO    A->B
				 *      ���ͣ� CC    A->B  C
				 *      ���ͣ� BCC   A->B  C
				 *  �������� ���͵ĵ�ַ
				 */
				mail.setRecipient(RecipientType.TO, new InternetAddress(usertable.getEmail()));
				
				//3.3 ��������
				mail.setSubject("�����ʼ�-������������̳ע���ʼ�");
				
				String html = "�װ���"+usertable.getUsername()+"�û�:<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ϲ���Ϊ������������̳�Ļ�Ա�����á����Զˡ�ʹ���������Ӽ�������û���<br/>";
				
				html += "<a href='http://localhost:8080/User/ActiveServlet?email="+usertable.getEmail()+"&code="+usertable.getValidatecode()+"'>����</a>";
				
				//3.4 ��������
				/**
				 * �������� �ʼ������ݸ�ʽ���� ��ͨ�ı���html��ʽ
				 */
				mail.setContent(html, "text/html;charset=utf-8");
				
				//4)�����ʼ�
				Transport.send(mail);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}



}
