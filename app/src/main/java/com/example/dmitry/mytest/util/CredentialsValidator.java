package com.example.dmitry.mytest.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class CredentialsValidator {
	public static boolean validateEmail(String text) {
		final String emailRegex = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." +
				"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f" +
				"\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@" +
				"(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|" +
				"\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]" +
				"|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:" +
				"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|" +
				"\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

		Pattern emailPattern = Pattern.compile(emailRegex);
		Matcher emailMather = emailPattern.matcher(text);
		return emailMather.matches();
	}

	public static boolean validatePassword(String text) {
		final String passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{6,}$";
		Pattern passwordPattern = Pattern.compile(passwordRegex);
		Matcher passwordMather = passwordPattern.matcher(text);
		return passwordMather.matches();
	}
}
