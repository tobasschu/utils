/*
   Copyright 2015 Tobias Schumacher

  import java.util.regex.Matcher;
import java.util.regex.Pattern;

   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
 */
package de.tschumacher.utils.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator {
	// https://howtodoinjava.com/regex/java-regex-validate-email-address/
	private static final String EMAIL_PATTERN = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

	public static boolean validate(final String hex) {

		Matcher matcher = Pattern.compile(EMAIL_PATTERN).matcher(hex);
		return matcher.matches();

	}
}
