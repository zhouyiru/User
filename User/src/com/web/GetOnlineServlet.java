package com.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.entity.OnLineBean;

public class GetOnlineServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		//1)��context����ȡ��map����
				Map<String,HttpSession> onLine = (Map<String,HttpSession>)
								this.getServletContext().getAttribute("onLine");
				
				//2)����һ���µ�List����
				List<OnLineBean> list = new ArrayList<OnLineBean>();
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				//3)����Map����
				if(onLine!=null){
					synchronized (GetOnlineServlet.class) {
						for(    Entry<String,HttpSession> entry  :  onLine.entrySet()    ){
								//��װ��¼�û���javabean����
								OnLineBean  bean = new OnLineBean();
								bean.setSessionId(entry.getKey());
								HttpSession session = entry.getValue();
								bean.setName((String)session.getAttribute("username"));
								bean.setIp((String)session.getAttribute("ip"));
								bean.setLoginTime(   sdf.format( new Date(session.getCreationTime()))       );
								bean.setLastTime(   sdf.format( new Date(session.getLastAccessedTime()))       );
								//�ѷ�װ�õ�javabean����list������
								list.add(bean);
						}
					}
				}
				HttpSession session = request.getSession(true);
				//3)��listת����jspҳ����
				session.setAttribute("list", list);
				request.getRequestDispatcher("online.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

	}

}
