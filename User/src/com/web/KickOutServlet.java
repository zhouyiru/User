package com.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * �߳��û���servlet
 * @author APPle
 *
 */
public class KickOutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1)���ܵ��߳���id
		String sessionId = request.getParameter("sessionId");
		
		//2)ǿ��ע��ָ��id���û�
		Map<String,HttpSession> onLine  =
			   (Map<String,HttpSession>)this.getServletContext().getAttribute("onLine");
		//��ѯ��Ҫע����session����
		HttpSession session = onLine.get(sessionId);
		if(session!=null){
			session.removeAttribute("username"); //�Զ�����ü��������Ƴ�map�е��Ѿ�ע�����û���Ϣ
			session.removeAttribute("ip");
		}
		
		response.sendRedirect("GetOnlineServlet");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
