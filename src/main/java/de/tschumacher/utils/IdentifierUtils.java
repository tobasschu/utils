package de.tschumacher.utils;

import java.nio.charset.Charset;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class IdentifierUtils {

	public static String createUniqueIdentifier(final String userId) {
		final HashFunction sha256 = Hashing.sha256();
		final HashCode hashString = sha256.hashString(
				userId + String.valueOf(System.currentTimeMillis()),
				Charset.defaultCharset());
		return hashString.toString();
	}
}
