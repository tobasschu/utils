package de.moovin.utils;

import java.io.File;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

  public static File convertToFile(final MultipartFile file) throws IllegalStateException,
      IOException {
    final File convFile = new File(file.getOriginalFilename());
    file.transferTo(convFile);
    return convFile;
  }

}
