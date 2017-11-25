package com.service.impl;

import java.util.Collection;

import com.bean.dao.MessageDB;
import com.bean.dao.impl.MessageDBImpl;
import com.bean.entity.Message;
import com.service.MessageService;

public class MessageServiceImpl implements MessageService {

	MessageDB messagedb = new MessageDBImpl();
	@Override
	public void postMessage(Message message) {
		// TODO Auto-generated method stub
          messagedb.postMessage(message);
	}

	@Override
	public void doUpdate(Message message) {
		// TODO Auto-generated method stub
           messagedb.doUpdate(message);
	}

	@Override
	public void doDelete(Message message) {
		// TODO Auto-generated method stub
          messagedb.doDelete(message);
	}

	@Override
	public Message showDital(int no) {
		// TODO Auto-generated method stub
		Message message = messagedb.showDital(no);
		return message;
	}

	@Override
	public Collection<Message> showMessage() {
		// TODO Auto-generated method stub
		Collection<Message> rt = messagedb.showMessage();
		return rt;
	}

	@Override
	public Collection<Message> pageSelect(int curPage) {
		// TODO Auto-generated method stub
		Collection<Message> rt = messagedb.pageSelect(curPage);
		return rt;
	}

}
