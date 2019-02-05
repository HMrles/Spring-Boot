package com.hmrles.exception;

import com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException;

/**
 * <h4>DuplicateEmailException</h4>
 * 
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0
 */
public class DuplicateEmailException extends AWSCognitoIdentityProviderException {

	/**
	 * long
	 */
	private static final long serialVersionUID = 6561571810771139916L;

	public DuplicateEmailException(final String errorMessage) {
		super(errorMessage);
	}
}
