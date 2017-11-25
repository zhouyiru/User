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
           //设置显示编码
		   response.setContentType("text/html;charset=utf-8");
		   //创建对象并实现接口
		   UserTable usertable = new UserTable(); 
		   UserTableService userService = new UserTableServiceImpl();	
		   //接收表单值
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
		   //封装bean中
	        usertable.setUsername(username);
	        usertable.setUserpwd(userpwd);
	        usertable.setUsergender(usergender);
	        usertable.setUseredu(useredu);
	        usertable.setEmail(email);
	        usertable.setUseraddress(useraddress);
	     
	        usertable.setValidatecode(com.util.WebUtil.uuid());
	  //判空
	  if(usertable.getUsername()!=null&&usertable.getUserpwd()!=null&&!usertable.getUsername().equals("")){
    	   userService.Regist(usertable);
    	     }
	  
	//启动线程
			new MySendMailThread(usertable).start();
			
       PrintWriter  out = response.getWriter();
       out.print("<meta  http-equiv='Content-Type' content='text/html;charset=utf-8'>");   
       out.print("<script>");
       out.print("alert('注册成功,请去邮箱激活用户');");
       out.print("window.location.href='Regist.jsp'");
       out.print("</script>");
       out.close();
    	 
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		doGet(request, response);

	}
	/**
	 * 使用线程发送邮件
	 */
class MySendMailThread extends Thread{
		
		private UserTable usertable;
		
		public MySendMailThread(UserTable usertable){
			this.usertable = usertable;
		}
		
		@Override
		public void run() {
			try {
				//1)创建一个Session对象,连接和登录服务器
				/**
				 * 参数一： 本次连接的配置。
				 * 参数二： 返回对用户名和密码base64加密的对象
				 */
				Properties props = new Properties();
				//1.1连接的发邮件的服务器地址
				props.setProperty("mail.host", "smtp.163.com");
				//1.2 指定进行验证登录
				props.setProperty("mail.smtp.auth", "true");
				
				Session  session = Session.getDefaultInstance(props, new Authenticator() {
							@Override
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication("zyr40318076@163.com","zyr40318076");
							}
				});
				//打开调用
				session.setDebug(true);
				
				//2)在本次连接上， 创建一封邮件
				MimeMessage mail = new MimeMessage(session);
				
				//3）设置邮件内容
				///3.1 设置发件人
				mail.setFrom(new InternetAddress("zyr40318076@163.com"));
				
				//3.2 设置收件人
				/**
				 * 参数一： 发送方法
				 * 		发送： TO    A->B
				 *      抄送： CC    A->B  C
				 *      密送： BCC   A->B  C
				 *  参数二： 发送的地址
				 */
				mail.setRecipient(RecipientType.TO, new InternetAddress(usertable.getEmail()));
				
				//3.3 设置主题
				mail.setSubject("激活邮件-意中人相亲论坛注册邮件");
				
				String html = "亲爱的"+usertable.getUsername()+"用户:<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;恭喜你成为意中人相亲论坛的会员，请用【电脑端】使用以下链接激活你的用户。<br/>";
				
				html += "<a href='http://localhost:8080/User/ActiveServlet?email="+usertable.getEmail()+"&code="+usertable.getValidatecode()+"'>激活</a>";
				
				//3.4 设置内容
				/**
				 * 参数二： 邮件的内容格式。如 普通文本，html方式
				 */
				mail.setContent(html, "text/html;charset=utf-8");
				
				//4)发送邮件
				Transport.send(mail);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}



}
