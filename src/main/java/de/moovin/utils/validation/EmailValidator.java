package de.moovin.utils.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {

	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static boolean validate(final String hex) {

		Matcher matcher = Pattern.compile(EMAIL_PATTERN).matcher(hex);
		return matcher.matches();

	}
}
