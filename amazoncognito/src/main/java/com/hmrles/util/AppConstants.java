package com.hmrles.util;

import com.amazonaws.regions.Regions;

/**
 * <h4>AppConstants</h4>
 * 
 * @author HMrles
 * @since Febrero 2019
 * @version 1.0
 */
public class AppConstants {
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

	public static final int USER_NAME_MIN_LENGTH = 4;
	public static final int PASSWORD_MIN_LENGTH = 8;

	public static final String USER_SESSION_ATTR = "user_info";

	public static final String USERNAME = "USERNAME";
	public static final String PASSWORD = "PASSWORD";
	public static final String NEW_PASSWORD = "NEW_PASSWORD";
	
	// Reemplazar con las configuración de AWS - Cognito
	public static final Regions REGION = Regions.US_EAST_1;
	public static final String LOCATION = "custom:location";
	public static final String EMAIL = "email";
	
	// 	Cognito IAM ID para acceso completo a Cognito
	public static final String COGNITO_ID = "AKIAJREBGJORT7DALM5Q";
	// Cognito IAM "secret key" para un acceso completo a Cognito
	public static final String COGNITO_KEY = "sHna92cZArGXbMkJobQfbYRDWkS4F3knQeICW0il";
	
	public static final String POOL_ID = "us-east-1_LOBfd9JvL";
	public static final String CLIENT_ID = "7jeu4p9jg7d5ap9duginmahej4";



}
