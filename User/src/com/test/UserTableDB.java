package com.test;

import static org.junit.Assert.*;

import org.junit.Test;



import com.bean.dao.impl.UserTableDBImpl;
import com.bean.entity.UserTable;

public class UserTableDB {
	UserTable usertable = new UserTable();
   UserTableDBImpl usertabledb = new UserTableDBImpl();
	@Test//���Ե�½�ɹ�
	public void  isLogin() throws Exception {
		usertable.setUsername("�˳�");
		usertable.setUserpwd("dengchao");
		boolean flag = usertabledb.isLogin(usertable);
		assertTrue(flag);
	}

}
	
