package com.bean.entity;

import java.sql.Date;

public class Message {

	private int no;
	private String title;
	private String name;
	private String content;
	private Date mdate;
	
	
	
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}



}
