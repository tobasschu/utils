package de.moovin.utils;

import org.springframework.web.multipart.MultipartFile;

public class FilePathUtils {
  public static String getFileExtension(final String filename) {
    return filename.substring(filename.lastIndexOf("."), filename.length());
  }


  public static String createRelativePath(final MultipartFile file, final String path,
      final String filename) {
    return path + filename + getFileExtension(file.getOriginalFilename());
  }


  public static String createRelativePathThumbnail(final MultipartFile file, final String path,
      final String filename, final String postfix) {
    return path + filename + postfix + getFileExtension(file.getOriginalFilename());
  }


  public static String createThumbnailPath(final String imagePath, final String thumbnailPostfix) {

    return imagePath.substring(0, imagePath.lastIndexOf(".")) + thumbnailPostfix
        + imagePath.substring(imagePath.lastIndexOf("."), imagePath.length());
  }


  public static String extractFileName(final String key) {
    if (key.lastIndexOf("/") < 0)
      return key;
    return key.substring(key.lastIndexOf("/") + 1, key.length());
  }

}
