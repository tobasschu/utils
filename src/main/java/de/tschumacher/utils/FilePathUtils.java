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
package de.tschumacher.utils;

import org.springframework.web.multipart.MultipartFile;

public class FilePathUtils {
	public static String getFileExtension(final String filename) {
		return filename.substring(filename.lastIndexOf("."), filename.length())
				.toLowerCase();
	}

	public static String createRelativePath(final MultipartFile file,
			final String path, final String filename) {
		return path + filename + getFileExtension(file.getOriginalFilename());
	}

	public static String createRelativePathThumbnail(final MultipartFile file,
			final String path, final String filename, final String postfix) {
		return path + filename + postfix
				+ getFileExtension(file.getOriginalFilename());
	}

	public static String createThumbnailPath(final String imagePath,
			final String thumbnailPostfix) {

		return imagePath.substring(0, imagePath.lastIndexOf("."))
				+ thumbnailPostfix
				+ imagePath.substring(imagePath.lastIndexOf("."),
						imagePath.length());
	}

	public static String extractFileName(final String key) {
		if (key.lastIndexOf("/") < 0)
			return key;
		return key.substring(key.lastIndexOf("/") + 1, key.length());
	}

	public static String createXMLFileName(final String filename) {
		return filename + ".xml";
	}

	public static String createZipFileName(final String filename) {
		return filename + ".zip";
	}

	public static String getFileExtensionOnly(final String filename) {

		return getFileExtension(filename).replace(".", "");
	}

}
