package de.moovin.utils.strings;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

public class FallbackMessageSource extends
		ReloadableResourceBundleMessageSource {

	final private Locale defaultLocale = Locale.ENGLISH;

	@Override
	protected MessageFormat resolveCode(final String code, final Locale locale) {
		MessageFormat result = super.resolveCode(code, locale);

		if ((result == null || result.toPattern().isEmpty())
				&& !locale.equals(this.defaultLocale)) {
			result = super.resolveCode(code, this.defaultLocale);
		}

		return result;
	}

	@Override
	protected String resolveCodeWithoutArguments(final String code,
			final Locale locale) {
		String result = super.resolveCodeWithoutArguments(code, locale);

		if ((result == null || result.isEmpty())
				&& !locale.equals(this.defaultLocale)) {
			result = super
					.resolveCodeWithoutArguments(code, this.defaultLocale);
		}

		return result;
	}

}
