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
      //设置接收编码
		response.setContentType("text/html;charset=utf-8");
        //创建对象实现接口
        MessageService messageService = new MessageServiceImpl();
        UserTableService usertableService = new UserTableServiceImpl();
       
        //获取no值
        String id = request.getParameter("no");
        int no = Integer.parseInt(id);
        Message message = messageService.showDital(no);
        String name = message.getName();
        //创建session域对象
        HttpSession session = request.getSession();
        //根据获取用户查询此人头像
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
