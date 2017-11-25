package com.service;

import java.sql.ResultSet;
import java.util.Collection;

import com.bean.entity.UserTable;



public interface UserTableService {
	
	public boolean isLogin(UserTable usertable);
	public void Regist(UserTable usertable);
	public void doUpdate(UserTable usertable);
	public void doDelete(int userid);
	public ResultSet doQuery(String SQL);
	public Collection<UserTable> pageSelect(int curPage,String find);
	public void updateImg(int userid,UserTable usertable);
	public ResultSet findUserByUsername(String username);
	public void updateCode(int userid);
	public ResultSet queryUser(String code);
}
