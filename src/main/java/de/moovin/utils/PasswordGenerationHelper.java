package de.moovin.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;

public class PasswordGenerationHelper {

	public static String generateSalt() {
		return RandomStringUtils.randomAlphanumeric(15);
	}

	public static String generateSalt(int length) {
		return RandomStringUtils.randomAlphanumeric(length);
	}

	public static String salt(String password, String salt)
			throws NoSuchAlgorithmException {
		String saltedPassword = password + "{" + salt + "}";
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		byte[] digest = messageDigest.digest(Utf8.encode(saltedPassword));
		return new String(Hex.encode(digest));
	}

}
