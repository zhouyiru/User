package com.test;



import static org.junit.Assert.*;

import org.junit.Test;

import com.bean.dao.impl.MessageDBImpl;
import com.bean.entity.Message;



public class MesssageDB {
	 Message message = new Message();
	   MessageDBImpl messagedb = new MessageDBImpl();
	@Test//测试修改message成功
	public void doUpdate() throws Exception {
		message.setNo(7);
		message.setContent("你要gg");
		messagedb.doUpdate(message);
		assertEquals("你要gg",message.getContent());
		
	}

}
