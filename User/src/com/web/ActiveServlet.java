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
 * �����û����߼�
 * 		1) �������뼤���Ƿ����
 *      2) ����Ƿ񳬹�48Сʱ 
 *      3�� ��������Ƿ���ȷ
 *      4) �����û����޸�user_list���statusֵ����Ϊ1��
 * 	
 * @author APPle
 *
 */
public class ActiveServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//�����û����͵Ĳ���
		String email = request.getParameter("email");
		String code  = request.getParameter("code");
		
		//1)�������뼤���Ƿ����
		UserTableService service = new UserTableServiceImpl();
		UserTable usertable = new UserTable();
		ResultSet rs  = service.queryUser(code);
		try {
			if(rs.next()){
			
					// 3)��������Ƿ���ȷ
				 email = rs.getString("email");
					usertable.setEmail(email);
					int userid = rs.getInt("userid");
					if(usertable.getEmail().equals(email)){
							// 4)�����û����޸�user_list���statusֵ����Ϊ1��
						service.updateCode(userid);
						
						request.setAttribute("msg", "����ɹ�,�������ӽ��е�½");
						request.getRequestDispatcher("/msg.jsp").forward(request, response);
						return;
					}else{
						//���䲻���ڵ�
						request.setAttribute("msg", "���䲻���ڣ�");
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
