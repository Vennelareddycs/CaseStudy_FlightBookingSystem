package com.capg.model;


public class EmailDetails {
	
	private String userName;
	private String toEmail;
	private String body;
	private String subject;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getToEmail() {
		return toEmail;
	}
	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public EmailDetails() {
		super();
	}
	public EmailDetails(String toEmail, String body, String subject) {
		super();
		this.toEmail = toEmail;
		this.body = body;
		this.subject = subject;
	}
	
	

}
