/*
   Copyright 2015 Tobias Schumacher

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package de.tschumacher.utils.strings;

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
