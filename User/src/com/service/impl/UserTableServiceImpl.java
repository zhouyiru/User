package com.service.impl;

import java.sql.ResultSet;
import java.util.Collection;

import com.bean.dao.UserTableDB;
import com.bean.dao.impl.UserTableDBImpl;
import com.bean.entity.UserTable;
import com.service.UserTableService;

public class UserTableServiceImpl implements UserTableService {

	/**
	 * ÒµÎñÂß¼­
	 */
	UserTableDB usertabledb = new UserTableDBImpl();
	
	public boolean isLogin(UserTable usertable) {
		// TODO Auto-generated method stub
		boolean tag = usertabledb.isLogin(usertable);
		return tag;
	}

	public void Regist(UserTable usertable) {
		// TODO Auto-generated method stub
        usertabledb.Regist(usertable);
	}

	public void doUpdate(UserTable usertable) {
		// TODO Auto-generated method stub
        usertabledb.doUpdate(usertable);
	}

	public void doDelete(int userid) {
		// TODO Auto-generated method stub
        usertabledb.doDelete(userid);
	}

	public ResultSet doQuery(String SQL) {
		// TODO Auto-generated method stub
		ResultSet rs = usertabledb.doQuery(SQL);
		return rs;
	}
	public Collection<UserTable> pageSelect(int curPage,String find){
		// TODO Auto-generated method stub
		Collection<UserTable> usertable = usertabledb.pageSelect(curPage, find);
		return usertable;
	}
	public void updateImg(int userid,UserTable usertable) {
		// TODO Auto-generated method stub
	  usertabledb.updateImg(userid,usertable);	
	}
	public ResultSet findUserByUsername(String username) {
		// TODO Auto-generated method stub
		ResultSet rs = usertabledb.findUserByUsername(username);
		return rs;
	}
	public ResultSet queryUser(String code){
		ResultSet rs = usertabledb.queryUser(code);
		return rs;
	}
	public void updateCode(int userid){
		usertabledb.updateCode(userid);
	}
	
}
