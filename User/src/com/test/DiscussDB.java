package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.bean.dao.impl.DiscussDBImpl;
import com.bean.entity.Discuss;

public class DiscussDB {

	Discuss discuss = new Discuss();
	DiscussDBImpl discussdb = new DiscussDBImpl();
	@Test//����discussɾ���ɹ�
	public void doDelete() throws Exception {
		discuss.setId(11);
		discussdb.doDelete(discuss);
		assertEquals(null,discuss.getContent());
	}
}
