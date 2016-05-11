/*
 * Copyright 2015 Tobias Schumacher
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package de.tschumacher.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

  private static final int READ_TIMEOUT = 30 * 1000;
  private static final int CONNECTION_TIMEOUT = 10 * 1000;

  public static File convertToFile(final MultipartFile file) throws IllegalStateException,
      IOException {
    final File convFile = new File(file.getOriginalFilename());
    file.transferTo(convFile);
    return convFile;
  }

  public static boolean isZipFile(File file) throws FileNotFoundException, IOException {
    FileInputStream in = new FileInputStream(file);
    ZipInputStream zipInputStream = new ZipInputStream(in);
    boolean isZipFile = zipInputStream.getNextEntry() != null;
    in.close();
    zipInputStream.close();
    return isZipFile;

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

  public static List<File> unzip(File zipFile, String outputFolder) throws IOException {
    byte[] buffer = new byte[1024];
    List<File> files = new ArrayList<File>();

    File folder = new File(outputFolder);
    if (!folder.exists()) {
      folder.mkdir();
    }

    ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
    ZipEntry ze = zis.getNextEntry();

    while (ze != null) {
      String fileName = ze.getName();
      File newFile = new File(outputFolder + File.separator + fileName);
      new File(newFile.getParent()).mkdirs();
      FileOutputStream fos = new FileOutputStream(newFile);

      int len;
      while ((len = zis.read(buffer)) > 0) {
        fos.write(buffer, 0, len);
      }

      fos.close();
      files.add(newFile);
      ze = zis.getNextEntry();
    }

    zis.closeEntry();
    zis.close();
    return files;

  }

  public static File createFileWithContent(final String filename, final String openImmoContent,
      Charset charset) throws IOException {
    final File file = new File(filename);
    final InputStream inputStream = new ByteArrayInputStream(openImmoContent.getBytes(charset));
    org.apache.commons.io.FileUtils.copyInputStreamToFile(inputStream, file);
    return file;
  }

  public static File createFileWithContent(final String filename, final String openImmoContent)
      throws IOException {
    return createFileWithContent(filename, openImmoContent, Charset.forName("UTF-8"));
  }



  public static File createFileFromUrl(final String fileName, final String url) throws IOException,
      MalformedURLException {
    final File file = new File(fileName);
    org.apache.commons.io.FileUtils.copyURLToFile(new URL(url), file, CONNECTION_TIMEOUT,
        READ_TIMEOUT);
    return file;
  }

  public static String createStringFromFile(final File attachment) throws IOException {
    return org.apache.commons.io.FileUtils.readFileToString(attachment);
  }

  public static void deleteFile(final File file) {
    file.delete();
  }


  public static void deleteFiles(final List<File> files) {
    for (final File file : files) {
      deleteFile(file);
    }

  }

}
