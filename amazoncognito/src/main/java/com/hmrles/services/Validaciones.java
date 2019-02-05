package com.hmrles.services;

/**
 * <h4>EmailValidator</h4>
 * <p>
 * Validate the syntax of an email address (of course this code cannot assure
 * that the email address actually connects to a working email account).
 * </p>
 * <p>
 * This code is based on code published on the code in this post:
 * </p>
 * 
 * <pre>
 * https://www.baeldung.com/registration-with-spring-mvc-and-spring-security
 * </pre>
 * <p>
 * The original code is copyright (c) 2017 Baeldung. The code is available under
 * the MIT open source license (see
 * https://github.com/Baeldung/spring-security-registration/blob/master/License.md).
 * </p>
 * <p>
 * Oct 24, 2018
 * </p>
 * 
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0s
 */
public interface Validaciones {

	boolean isEmailValid(String email);
	boolean isUserValid(String user);
	boolean isLocationValid(String location);
	boolean isPasswordValid(String password);

}
