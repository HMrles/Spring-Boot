package com.hmrles.model;

/**
 * <h4>Login</h4>
 * 
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0
 */
public class Login extends User {
	private Boolean newPasswordRequired = false;

	public Login(String userName, String email, String location) {
		super(userName, email, location);
	}

	public Login(User info) {
		this(info.getUserName(), info.getEmailAddr(), info.getLocation());
	}

	public Boolean getNewPasswordRequired() {
		return newPasswordRequired;
	}

	public void setNewPasswordRequired(Boolean newPasswordRequired) {
		this.newPasswordRequired = newPasswordRequired;
	}

}
