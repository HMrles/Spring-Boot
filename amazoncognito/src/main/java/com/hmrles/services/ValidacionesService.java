package com.hmrles.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.hmrles.util.AppConstants;

@Service
public class ValidacionesService implements Validaciones {

	@Override
	public boolean isEmailValid(String email) {
		if (Strings.isNullOrEmpty(email)) {
			return false;
		} else {
			Pattern pattern = Pattern.compile(AppConstants.EMAIL_PATTERN);
			Matcher matcher = pattern.matcher(email);
			return matcher.matches();
		}

	}

	@Override
	public boolean isUserValid(String user) {
		if (Strings.isNullOrEmpty(user)) {
			return false;
		} else {
			if (user.trim().length() >= AppConstants.USER_NAME_MIN_LENGTH) {
				return true;
			} else
				return false;
		}
	}

	@Override
	public boolean isLocationValid(String location) {
		if (Strings.isNullOrEmpty(location)) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean isPasswordValid(String password) {
		if (Strings.isNullOrEmpty(password)) {
			return false;
		} else {
			return true;
		}
	}

}
