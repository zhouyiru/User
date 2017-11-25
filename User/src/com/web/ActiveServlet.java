package com.web;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.entity.UserTable;
import com.service.UserTableService;
import com.service.impl.UserTableServiceImpl;
/**
 * 激活用户的逻辑
 * 		1) 检查随机码激活是否存在
 *      2) 检查是否超过48小时 
 *      3） 检查邮箱是否正确
 *      4) 激活用户（修改user_list表的status值，改为1）
 * 	
 * @author APPle
 *
 */
public class ActiveServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接收用户发送的参数
		String email = request.getParameter("email");
		String code  = request.getParameter("code");
		
		//1)检查随机码激活是否存在
		UserTableService service = new UserTableServiceImpl();
		UserTable usertable = new UserTable();
		ResultSet rs  = service.queryUser(code);
		try {
			if(rs.next()){
			
					// 3)检查邮箱是否正确
				 email = rs.getString("email");
					usertable.setEmail(email);
					int userid = rs.getInt("userid");
					if(usertable.getEmail().equals(email)){
							// 4)激活用户（修改user_list表的status值，改为1）
						service.updateCode(userid);
						
						request.setAttribute("msg", "激活成功,请点击链接进行登陆");
						request.getRequestDispatcher("/msg.jsp").forward(request, response);
						return;
					}else{
						//邮箱不存在的
						request.setAttribute("msg", "邮箱不存在！");
						request.getRequestDispatcher("/msg.jsp").forward(request, response);
						return;
				
			          }
					
}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	


}
