package com.hmrles.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.amazonaws.SdkBaseException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AWSCognitoIdentityProviderException;
import com.amazonaws.services.cognitoidp.model.AdminCreateUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminDeleteUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminGetUserRequest;
import com.amazonaws.services.cognitoidp.model.AdminGetUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeRequest;
import com.amazonaws.services.cognitoidp.model.AdminRespondToAuthChallengeResult;
import com.amazonaws.services.cognitoidp.model.AdminUpdateUserAttributesRequest;
import com.amazonaws.services.cognitoidp.model.AdminUserGlobalSignOutRequest;
import com.amazonaws.services.cognitoidp.model.AttributeType;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.amazonaws.services.cognitoidp.model.AuthenticationResultType;
import com.amazonaws.services.cognitoidp.model.ChallengeNameType;
import com.amazonaws.services.cognitoidp.model.ChangePasswordRequest;
import com.amazonaws.services.cognitoidp.model.ChangePasswordResult;
import com.amazonaws.services.cognitoidp.model.CodeDeliveryDetailsType;
import com.amazonaws.services.cognitoidp.model.ConfirmForgotPasswordRequest;
import com.amazonaws.services.cognitoidp.model.ConfirmForgotPasswordResult;
import com.amazonaws.services.cognitoidp.model.ForgotPasswordRequest;
import com.amazonaws.services.cognitoidp.model.ForgotPasswordResult;
import com.amazonaws.services.cognitoidp.model.ListUsersRequest;
import com.amazonaws.services.cognitoidp.model.ListUsersResult;
import com.amazonaws.services.cognitoidp.model.UserType;
import com.hmrles.exception.DuplicateEmailException;
import com.hmrles.model.Login;
import com.hmrles.model.Password;
import com.hmrles.model.PasswordReset;
import com.hmrles.model.Session;
import com.hmrles.model.User;
import com.hmrles.util.AppConstants;

/**
 * <h4>AuthenticationService</h4>
 * <p>
 * Proporciona servicios para la gestion de usuarios con AWs- Cognito
 * </p>
 * 
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0
 */
@Service
public class AuthenticationService implements Authentication {


	protected static AWSCognitoIdentityProvider mIdentityProvider = null;

	public AuthenticationService() {
		if (mIdentityProvider == null) {
			mIdentityProvider = getAmazonCognitoIdentityClient();
		}
	}

	/**
	 * <p>
	 * Build an AWSCredentials object from the ID and secret key.
	 * </p>
	 * 
	 * @param AWS_ID
	 * @param AWS_KEY
	 * @return an AWSCredentials object, initialized with the ID and Key.
	 */
	protected AWSCredentials getCredentials(String AWS_ID, String AWS_KEY) {
		AWSCredentials credentials = new BasicAWSCredentials(AWS_ID, AWS_KEY);
		return credentials;
	}

	/**
	 * <p>
	 * Build an AWS cognito identity provider, based on the parameters defined in
	 * the CognitoResources interface.
	 * </p>
	 * 
	 * @return
	 */
	protected AWSCognitoIdentityProvider getAmazonCognitoIdentityClient() {
		AWSCredentials credentials = getCredentials(AppConstants.COGNITO_ID, AppConstants.COGNITO_KEY);
		AWSCredentialsProvider credProvider = new AWSStaticCredentialsProvider(credentials);
		AWSCognitoIdentityProvider client = AWSCognitoIdentityProviderClientBuilder.standard()
				.withCredentials(credProvider).withRegion(AppConstants.REGION).build();
		return client;
	}


	@Override
	public void createUser(final User userInfo) throws AWSCognitoIdentityProviderException {
		final String emailAddr = userInfo.getEmailAddr();
		if (emailAddr != null && emailAddr.length() > 0) {
			
			User info = findUserByEmail(emailAddr);
			if (info == null) {
				AdminCreateUserRequest cognitoRequest = new AdminCreateUserRequest()
						.withUserPoolId(AppConstants.POOL_ID).withUsername(userInfo.getUserName())
						.withUserAttributes(new AttributeType().withName(AppConstants.EMAIL).withValue(emailAddr),
								new AttributeType().withName(AppConstants.LOCATION)
										.withValue(userInfo.getLocation()),
								new AttributeType().withName("email_verified").withValue("true"));

				mIdentityProvider.adminCreateUser(cognitoRequest);
			} else {
				
				throw new DuplicateEmailException("El email " + emailAddr + " ya está registrado.");
			}
		}
	} 


	@Override
	public void deleteUser(final String userName, final String password) throws AWSCognitoIdentityProviderException {
		Session sessionInfo = sessionLogin(userName, password);
		if (sessionInfo != null) {
			AdminDeleteUserRequest deleteRequest = new AdminDeleteUserRequest().withUsername(userName)
					.withUserPoolId(AppConstants.POOL_ID);
			mIdentityProvider.adminDeleteUser(deleteRequest);
		}
	}


	@Override
	public void updateUser(final User newInfo) throws AWSCognitoIdentityProviderException {
		AdminUpdateUserAttributesRequest updateRequest = new AdminUpdateUserAttributesRequest()
				.withUsername(newInfo.getUserName()).withUserPoolId(AppConstants.POOL_ID).withUserAttributes(
						new AttributeType().withName(AppConstants.LOCATION).withValue(newInfo.getLocation()));
		mIdentityProvider.adminUpdateUserAttributes(updateRequest);
	}


	protected Session sessionLogin(final String userName, final String password)
			throws AWSCognitoIdentityProviderException {
		Session info = null;
		HashMap<String, String> authParams = new HashMap<String, String>();
		authParams.put("USERNAME", userName);
		authParams.put("PASSWORD", password);
		AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
				.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withUserPoolId(AppConstants.POOL_ID)
				.withClientId(AppConstants.CLIENT_ID).withAuthParameters(authParams);
		AdminInitiateAuthResult authResult = mIdentityProvider.adminInitiateAuth(authRequest);		
		if (authResult != null) {
			final String session = authResult.getSession();
			String accessToken = null;
			AuthenticationResultType resultType = authResult.getAuthenticationResult();
			if (resultType != null) {
				accessToken = resultType.getAccessToken();
			}
			final String challengeResult = authResult.getChallengeName();
			info = new Session(session, accessToken, challengeResult);
		}
		return info;
	}


	@Override
	public Login userLogin(final String userName, final String password)
			throws AWSCognitoIdentityProviderException {
		Login loginInfo = null;
		Session sessionInfo = sessionLogin(userName, password);
		
		if (sessionInfo != null) {
			User userInfo = getUserInfo(userName);
			loginInfo = new Login(userInfo);
			
			String challengeResult = sessionInfo.getChallengeResult();
			if (challengeResult != null && challengeResult.length() > 0) {
				loginInfo
						.setNewPasswordRequired(challengeResult.equals(ChallengeNameType.NEW_PASSWORD_REQUIRED.name()));
			}
		}
		return loginInfo;
	}


	@Override
	public void userLogout(final String userName) throws AWSCognitoIdentityProviderException {
		AdminUserGlobalSignOutRequest signOutRequest = new AdminUserGlobalSignOutRequest().withUsername(userName)
				.withUserPoolId(AppConstants.POOL_ID);
		mIdentityProvider.adminUserGlobalSignOut(signOutRequest);
	}

	
	@Override
	public void changePassword(final Password passwordRequest) throws AWSCognitoIdentityProviderException {
		
		final Session sessionInfo = sessionLogin(passwordRequest.getUserName(), passwordRequest.getOldPassword());
		if (sessionInfo != null && sessionInfo.getAccessToken() != null) {
			ChangePasswordRequest changeRequest = new ChangePasswordRequest()
					.withAccessToken(sessionInfo.getAccessToken())
					.withPreviousPassword(passwordRequest.getOldPassword())
					.withProposedPassword(passwordRequest.getNewPassword());
			ChangePasswordResult rslt = mIdentityProvider.changePassword(changeRequest);
		} else {
			String msg = "Access token was not returned from session login";
			throw new AWSCognitoIdentityProviderException(msg);
		}
	}

	
	@Override
	public void changeEmail(final String userName, final String newEmailAddr)
			throws AWSCognitoIdentityProviderException {
		AdminUpdateUserAttributesRequest updateRequest = new AdminUpdateUserAttributesRequest().withUsername(userName)
				.withUserPoolId(AppConstants.POOL_ID)
				.withUserAttributes(new AttributeType().withName(AppConstants.EMAIL).withValue(newEmailAddr),
						new AttributeType().withName("email_verified").withValue("true"));
		mIdentityProvider.adminUpdateUserAttributes(updateRequest);
	}

	
	@Override
	public void changeTemporaryPassword(final Password passwordRequest)
			throws AWSCognitoIdentityProviderException {
		
		final Session sessionInfo = sessionLogin(passwordRequest.getUserName(), passwordRequest.getOldPassword());
		final String sessionString = sessionInfo.getSession();
		if (sessionString != null && sessionString.length() > 0) {
			Map<String, String> challengeResponses = new HashMap<String, String>();
			challengeResponses.put(AppConstants.USERNAME, passwordRequest.getUserName());
			challengeResponses.put(AppConstants.PASSWORD, passwordRequest.getOldPassword());
			challengeResponses.put(AppConstants.NEW_PASSWORD, passwordRequest.getNewPassword());
			AdminRespondToAuthChallengeRequest changeRequest = new AdminRespondToAuthChallengeRequest()
					.withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
					.withChallengeResponses(challengeResponses).withClientId(AppConstants.CLIENT_ID)
					.withUserPoolId(AppConstants.POOL_ID).withSession(sessionString);
			AdminRespondToAuthChallengeResult challengeResponse = mIdentityProvider
					.adminRespondToAuthChallenge(changeRequest);
		}
	} 

	
	@Override
	public void resetPassword(PasswordReset resetRequest) throws AWSCognitoIdentityProviderException {
		ConfirmForgotPasswordRequest passwordRequest = new ConfirmForgotPasswordRequest()
				.withUsername(resetRequest.getUserName()).withConfirmationCode(resetRequest.getResetCode())
				.withClientId(AppConstants.CLIENT_ID).withPassword(resetRequest.getNewPassword());
		ConfirmForgotPasswordResult rslt = mIdentityProvider.confirmForgotPassword(passwordRequest);
	}


	@Override
	public void forgotPassword(final String userName) throws AWSCognitoIdentityProviderException {
		ForgotPasswordRequest passwordRequest = new ForgotPasswordRequest().withClientId(AppConstants.CLIENT_ID)
				.withUsername(userName);
		ForgotPasswordResult rslt = mIdentityProvider.forgotPassword(passwordRequest);
		CodeDeliveryDetailsType delivery = rslt.getCodeDeliveryDetails();
	}

	
	@Override
	public User getUserInfo(final String userName) throws AWSCognitoIdentityProviderException {
		AdminGetUserRequest userRequest = new AdminGetUserRequest().withUsername(userName)
				.withUserPoolId(AppConstants.POOL_ID);
		AdminGetUserResult userResult = mIdentityProvider.adminGetUser(userRequest);
		List<AttributeType> userAttributes = userResult.getUserAttributes();
		final String rsltUserName = userResult.getUsername();
		String emailAddr = null;
		String location = null;
		for (AttributeType attr : userAttributes) {
			if (attr.getName().equals(AppConstants.EMAIL)) {
				emailAddr = attr.getValue();
			} else if (attr.getName().equals(AppConstants.LOCATION)) {
				location = attr.getValue();
			}
		}
		User info = null;
		if (rsltUserName != null && emailAddr != null && location != null) {
			info = new User(rsltUserName, emailAddr, location);
		}
		return info;
	} 

	
	@Override
	public User findUserByEmail(String email) throws AWSCognitoIdentityProviderException {
		User info = null;
		if (email != null && email.length() > 0) {
			final String emailQuery = "email=\"" + email + "\"";
			ListUsersRequest usersRequest = new ListUsersRequest().withUserPoolId(AppConstants.POOL_ID)
					.withAttributesToGet(AppConstants.EMAIL, AppConstants.LOCATION).withFilter(emailQuery);
			ListUsersResult usersRslt = mIdentityProvider.listUsers(usersRequest);
			List<UserType> users = usersRslt.getUsers();
			if (users != null && users.size() > 0) {
			
				if (users.size() == 1) {
					UserType user = users.get(0);
					final String userName = user.getUsername();
					String emailAddr = null;
					String location = null;
					List<AttributeType> attributes = user.getAttributes();
					if (attributes != null) {
						for (AttributeType attr : attributes) {
							if (attr.getName().equals(AppConstants.EMAIL)) {
								emailAddr = attr.getValue();
							} else if (attr.getName().equals(AppConstants.LOCATION)) {
								location = attr.getValue();
							}
						}
						if (userName != null && emailAddr != null && location != null) {
							info = new User(userName, emailAddr, location);
						}
					}
				} else {
					throw new DuplicateEmailException("Más de un usuario tiene la dirección de correo electrónico " + email);
				}
			}
		}
		return info;
	}


	@Override
	public boolean hasUser(final String userName) {
		boolean userExists = false;
		try {
			User info = getUserInfo(userName);
			if (info != null && info.getUserName() != null && info.getUserName().length() > 0
					&& info.getUserName().equals(userName)) {
				userExists = true;
			}
		} catch (SdkBaseException ex) {
		}
		return userExists;
	}

}
