package com.bean.entity;
/**
 * �ö������ڷ�װ��¼�û�����������
 * @author APPle
 *
 */
public class OnLineBean {
	private String sessionId;//session�����Id
	private String name;//��¼��
	private String ip;//ip
	private String loginTime;//��¼ʱ��
	private String lastTime;//������ʱ��
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	
	
	
	
}

