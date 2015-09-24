package de.moovin.utils;

import org.springframework.web.multipart.MultipartFile;

public class FilePathUtils {
	public static String getFileExtension(String filename) {
		return filename.substring(filename.lastIndexOf("."), filename.length());
	}

	public static String createRelativePath(MultipartFile file, String path,
			String filename) {
		return path + filename + getFileExtension(file.getOriginalFilename());
	}

	public static String createRelativePathThumbnail(MultipartFile file,
			String path, String filename, String postfix) {
		return path + filename + postfix
				+ getFileExtension(file.getOriginalFilename());
	}

	public static String createThumbnailPath(String imagePath,
			String thumbnailPostfix) {

		return imagePath.substring(0, imagePath.lastIndexOf("."))
				+ thumbnailPostfix
				+ imagePath.substring(imagePath.lastIndexOf("."),
						imagePath.length());
	}

	public static String extractFileName(String key) {
		return key.substring(key.lastIndexOf("/"), key.length());
	}

}
