package com.hmrles.model;

/**
 * <h4>PasswordReset</h4>
 * 
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0
 */
public class PasswordReset {
	private String userName;
	private String resetCode;
	private String newPassword;

	public PasswordReset(String userName, String resetCode, String newPassword) {
		this.userName = userName;
		this.resetCode = resetCode;
		this.newPassword = newPassword;
	}

	public String getUserName() {
		return userName;
	}

	public String getResetCode() {
		return resetCode;
	}

	public String getNewPassword() {
		return newPassword;
	}

}
