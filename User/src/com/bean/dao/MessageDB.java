package com.bean.dao;

import java.util.Collection;

import com.bean.entity.Message;

public interface MessageDB {

	public void postMessage(Message message);
	public void doUpdate(Message message);
	public void doDelete(Message message);
	public Message showDital(int no);
	public Collection<Message> showMessage();
	public Collection<Message> pageSelect(int curPage);
}
