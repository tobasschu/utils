package de.moovin.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

  public static File convertToFile(final MultipartFile file) throws IllegalStateException,
      IOException {
    final File convFile = new File(file.getOriginalFilename());
    file.transferTo(convFile);
    return convFile;
  }


  public static File zip(final List<File> files, final String filename) throws IOException {

    final File targetZipFile = new File(filename);
    final FileOutputStream fos = new FileOutputStream(targetZipFile);
    final ZipOutputStream zos = new ZipOutputStream(fos);

    final byte[] buffer = new byte[128];
    for (int i = 0; i < files.size(); i++) {
      final File currentFile = files.get(i);
      if (!currentFile.isDirectory()) {
        final ZipEntry entry = new ZipEntry(currentFile.getName());
        final FileInputStream fis = new FileInputStream(currentFile);
        zos.putNextEntry(entry);
        int read = 0;
        while ((read = fis.read(buffer)) != -1) {
          zos.write(buffer, 0, read);
        }
        zos.closeEntry();
        fis.close();
      }
    }
    zos.close();
    fos.close();
    return targetZipFile;
  }


  public static File createFileWithContent(final String filename, final String openImmoContent)
      throws IOException {
    final File file = new File(filename);
    final InputStream inputStream =
        new ByteArrayInputStream(openImmoContent.getBytes(Charset.forName("UTF-8")));
    org.apache.commons.io.FileUtils.copyInputStreamToFile(inputStream, file);
    return file;
  }


  public static File createFileFromUrl(final String fileName, final String url) throws IOException,
      MalformedURLException {
    final File file = new File(fileName);
    org.apache.commons.io.FileUtils.copyURLToFile(new URL(url), file);
    return file;
  }


  public static String createStringFromFile(final File attachment) throws IOException {
    return org.apache.commons.io.FileUtils.readFileToString(attachment);
  }
}
