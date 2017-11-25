package com.listener;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class OnlineListener implements HttpSessionAttributeListener {

	/**
	 * ���û���¼�ɹ��󣬻�ִ��session.setAttribute("user", userName)
	 * �÷������ڼ����û���user���Ե����
	 */
	public void attributeAdded(HttpSessionBindingEvent se) {
		//�õ�������
		String username = se.getName();
		ServletContext context = se.getSession().getServletContext();
		
		if("username".equals(username)){
			//ͬ�������Ϊ�˱�������¼�û�ͬʱ����onLine����ʱ������������
			synchronized (OnlineListener.class) {
				//1)�ѵ�ǰ��¼��session�����װ��Map������
				//1.1 �ȴ�context���л�ȡsession����
				Map<String,HttpSession> onLine = 
						(Map<String,HttpSession>)context.getAttribute("onLine");
			
				//1.2 �������վ�ĵ�һ����¼�û���onLineΪnull����ʱ�½�һ��Map����
				if(onLine==null){
					onLine = new HashMap<String,HttpSession>(); 
				}
				//1.3 �ѵ�ǰ�û���session����Map����
				HttpSession session = se.getSession();
				onLine.put(session.getId(), session);   
				
				//2)�ѷ�װ�õ�map���浽context����
				context.setAttribute("onLine", onLine);  
			}
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		
	}

	/**
	 * �÷����ڵ���removeAttribute����ʱ����
	 *  ע����ʱ��һ�����Ƴ�user�������Ƶ�����
	 */
	public void attributeRemoved(HttpSessionBindingEvent se) {
		String username = se.getName();
		ServletContext context = se.getSession().getServletContext();
		String sessionId = se.getSession().getId();
		if("username".equals(username)){
			//1)��ȡcontext���е�map����
			Map<String,HttpSession> onLine
			 			= (Map<String,HttpSession>)context.getAttribute("onLine");
			
			//2)�Ƴ���Ӧ��session����
			onLine.remove(sessionId);
		
			//3�����޸ĺ��map���浽��context����
			context.setAttribute("onLine", onLine);
		}
	}
}

	


