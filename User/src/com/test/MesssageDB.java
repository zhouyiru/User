package com.test;



import static org.junit.Assert.*;

import org.junit.Test;

import com.bean.dao.impl.MessageDBImpl;
import com.bean.entity.Message;



public class MesssageDB {
	 Message message = new Message();
	   MessageDBImpl messagedb = new MessageDBImpl();
	@Test//�����޸�message�ɹ�
	public void doUpdate() throws Exception {
		message.setNo(7);
		message.setContent("��Ҫgg");
		messagedb.doUpdate(message);
		assertEquals("��Ҫgg",message.getContent());
		
	}

}
