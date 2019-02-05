package com.hmrles.model;

/**
 * <h4>User</h4>
 * 
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0
 */
public class User {
	private String userName;
	private String emailAddr;
	private String location;

	public User(String userName, String emailAddr, String location) {
		this.userName = userName;
		this.emailAddr = emailAddr;
		this.location = location;
	}

	public String getUserName() {
		return userName;
	}

	public String getEmailAddr() {
		return emailAddr;
	}

	public String getLocation() {
		return location;
	}

}
