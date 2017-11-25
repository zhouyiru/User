package com.listener;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class OnlineListener implements HttpSessionAttributeListener {

	/**
	 * 当用户登录成功后，会执行session.setAttribute("user", userName)
	 * 该方法用于监听用户的user属性的添加
	 */
	public void attributeAdded(HttpSessionBindingEvent se) {
		//得到属性名
		String username = se.getName();
		ServletContext context = se.getSession().getServletContext();
		
		if("username".equals(username)){
			//同步代码块为了避免多个登录用户同时操作onLine数据时引发并发问题
			synchronized (OnlineListener.class) {
				//1)把当前登录的session对象封装到Map集合中
				//1.1 先从context域中获取session数据
				Map<String,HttpSession> onLine = 
						(Map<String,HttpSession>)context.getAttribute("onLine");
			
				//1.2 如果是网站的第一个登录用户，onLine为null，这时新建一个Map集合
				if(onLine==null){
					onLine = new HashMap<String,HttpSession>(); 
				}
				//1.3 把当前用户的session存入Map集合
				HttpSession session = se.getSession();
				onLine.put(session.getId(), session);   
				
				//2)把封装好的map保存到context域中
				context.setAttribute("onLine", onLine);  
			}
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent se) {
		
	}

	/**
	 * 该方法在调用removeAttribute（）时触发
	 *  注销的时候，一定会移除user属性名称的数据
	 */
	public void attributeRemoved(HttpSessionBindingEvent se) {
		String username = se.getName();
		ServletContext context = se.getSession().getServletContext();
		String sessionId = se.getSession().getId();
		if("username".equals(username)){
			//1)获取context域中的map集合
			Map<String,HttpSession> onLine
			 			= (Map<String,HttpSession>)context.getAttribute("onLine");
			
			//2)移除对应的session对象
			onLine.remove(sessionId);
		
			//3）把修改后的map保存到到context域中
			context.setAttribute("onLine", onLine);
		}
	}
}

	


