package com.bean.entity;




public class UserTable {
private int userid;
private String username;
private String userpwd;
private int userage;
private String usergender;
private String useredu;
private long userphone;
private String useraddress;
private String userimg;
private String email;

private int status;
private String validatecode;



public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String getValidatecode() {
	return validatecode;
}
public void setValidatecode(String validatecode) {
	this.validatecode = validatecode;
}

public String getUserimg() {
	return userimg;
}
public void setUserimg(String userimg) {
	this.userimg = userimg;
}
public int getUserid() {
	return userid;
}
public String getUseredu() {
	return useredu;
}
public void setUseredu(String useredu) {
	this.useredu = useredu;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getUserpwd() {
	return userpwd;
}
public void setUserpwd(String userpwd) {
	this.userpwd = userpwd;
}
public int getUserage() {
	return userage;
}
public void setUserage(int userage) {
	this.userage = userage;
}
public String getUsergender() {
	return usergender;
}
public void setUsergender(String usergender) {
	this.usergender = usergender;
}
public long getUserphone() {
	return userphone;
}
public void setUserphone(long userphone) {
	this.userphone = userphone;
}
public String getUseraddress() {
	return useraddress;
}
public void setUseraddress(String useraddress) {
	this.useraddress = useraddress;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

}
