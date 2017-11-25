package com.web;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.entity.Message;
import com.service.DiscussService;
import com.service.MessageService;
import com.service.UserTableService;
import com.service.impl.DiscussServiceImpl;
import com.service.impl.MessageServiceImpl;
import com.service.impl.UserTableServiceImpl;

public class showDitalServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
      //���ý��ձ���
		response.setContentType("text/html;charset=utf-8");
        //��������ʵ�ֽӿ�
        MessageService messageService = new MessageServiceImpl();
        UserTableService usertableService = new UserTableServiceImpl();
       
        //��ȡnoֵ
        String id = request.getParameter("no");
        int no = Integer.parseInt(id);
        Message message = messageService.showDital(no);
        String name = message.getName();
        //����session�����
        HttpSession session = request.getSession();
        //���ݻ�ȡ�û���ѯ����ͷ��
        ResultSet rs = null;
        try {
        	rs = usertableService.findUserByUsername(name);
			while(rs.next()){
				String userimg = rs.getString("userimg");
		       session.setAttribute("userimg", userimg);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
       
        session.setAttribute("no", message.getNo());
        session.setAttribute("name", message.getName());
        session.setAttribute("content", message.getContent());
        session.setAttribute("mdate", message.getMdate());
        session.setAttribute("title", message.getTitle());
        
        
        
        request.getRequestDispatcher("showDital.jsp").forward(request, response);
        
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
doGet(request, response);
	}

}
