package de.tschumacher.utils;

import java.nio.charset.Charset;

import org.apache.commons.lang3.RandomStringUtils;

import com.google.common.hash.HashCode;
import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

public class IdentifierUtils {

  public static String createUniqueIdentifier(final String identifier) {
    final HashFunction sha256 = Hashing.sha256();
    final HashCode hashString =
        sha256.hashString(identifier + String.valueOf(System.currentTimeMillis()),
            Charset.defaultCharset());
    return hashString.toString();
  }

  public static String createIdentifier(int numberOfLetter, int numberOfNumbers) {
    return RandomStringUtils.randomAlphabetic(numberOfLetter).toUpperCase()
        + RandomStringUtils.randomNumeric(numberOfNumbers);
  }

}
