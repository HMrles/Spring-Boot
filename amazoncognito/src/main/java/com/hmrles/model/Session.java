package com.hmrles.model;

/**
 * <h4>Session</h4>
 * 
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0
 */
public class Session {
	private String session;
	private String accessToken;
	private String challengeResult;

	public Session(String session, String accessToken, String challengeResult) {
		this.session = session;
		this.accessToken = accessToken;
		this.challengeResult = challengeResult;
	}

	public String getSession() {
		return session;
	}

	public String getChallengeResult() {
		return challengeResult;
	}

	public String getAccessToken() {
		return accessToken;
	}

}
