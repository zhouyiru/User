package com.bean.entity;

import java.util.Date;

public class Discuss {

	private int no;
	private String name;
	private String content;
	private Date mdate;
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
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
