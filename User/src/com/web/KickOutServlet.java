package com.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 踢除用户的servlet
 * @author APPle
 *
 */
public class KickOutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1)接受到踢除的id
		String sessionId = request.getParameter("sessionId");
		
		//2)强制注销指定id的用户
		Map<String,HttpSession> onLine  =
			   (Map<String,HttpSession>)this.getServletContext().getAttribute("onLine");
		//查询需要注销的session对象
		HttpSession session = onLine.get(sessionId);
		if(session!=null){
			session.removeAttribute("username"); //自动会调用监听器，移除map中的已经注销的用户信息
			session.removeAttribute("ip");
		}
		
		response.sendRedirect("GetOnlineServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
