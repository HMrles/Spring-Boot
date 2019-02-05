package com.hmrles.model;

/**
 * <h4>Password</h4>
 * 
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0
 */
public class Password {
	private String userName;
	private String oldPassword;
	private String newPassword;

	public Password(String userName, String oldPassword, String newPassword) {
		this.userName = userName;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}

	public String getUserName() {
		return userName;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

}
