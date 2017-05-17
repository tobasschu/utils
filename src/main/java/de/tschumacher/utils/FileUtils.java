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
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

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
