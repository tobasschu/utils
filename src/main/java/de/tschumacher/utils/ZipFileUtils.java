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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;

public class ZipFileUtils {


  private static final String INDEX_DIVIDER = "_";

  public static boolean isZipFile(File file) throws FileNotFoundException, IOException {
    final FileInputStream in = new FileInputStream(file);
    final ZipInputStream zipInputStream = new ZipInputStream(in);
    final boolean isZipFile = zipInputStream.getNextEntry() != null;
    in.close();
    zipInputStream.close();
    return isZipFile;

  }

  public static File zip(final List<File> files, final String filename) throws IOException {
    final Set<String> usedFileNames = new HashSet<String>();
    final File targetZipFile = new File(filename);
    final FileOutputStream fos = new FileOutputStream(targetZipFile);
    final ZipOutputStream zos = new ZipOutputStream(fos);
    final byte[] buffer = new byte[128];

    for (final File currentFile : files) {
      if (!currentFile.isDirectory()) {
        zos.putNextEntry(new ZipEntry(createFileName(usedFileNames, files, currentFile)));
        writeZipFileEntry(zos, buffer, currentFile);
      }
    }
    zos.close();
    fos.close();
    return targetZipFile;
  }

  private static void writeZipFileEntry(final ZipOutputStream zos, final byte[] buffer,
      final File currentFile) throws IOException {
    final FileInputStream fis = new FileInputStream(currentFile);
    int read = 0;
    while ((read = fis.read(buffer)) != -1) {
      zos.write(buffer, 0, read);
    }
    zos.closeEntry();
    fis.close();
  }

  private static String createFileName(Set<String> usedFileNames, List<File> files, File currentFile) {
    String fileName = currentFile.getName();
    if (containsDuplicateFileName(files, currentFile) && usedFileNames.contains(fileName)) {
      fileName = createAlternativeFileName(usedFileNames, fileName);
      usedFileNames.add(fileName);
    }
    usedFileNames.add(fileName);
    return fileName;
  }

  private static String createAlternativeFileName(Set<String> usedFileNames, String fileName) {
    String currentFileName = fileName;
    int index = 1;
    do {
      currentFileName = createNextFileName(fileName, index);
      index++;
    } while (usedFileNames.contains(currentFileName));
    return currentFileName;
  }

  private static String createNextFileName(String fileName, int index) {
    final StringBuilder nextFileName = new StringBuilder();
    nextFileName.append(FilenameUtils.getBaseName(fileName));
    nextFileName.append(INDEX_DIVIDER);
    nextFileName.append(index);
    nextFileName.append(FilenameUtils.EXTENSION_SEPARATOR);
    nextFileName.append(FilenameUtils.getExtension(fileName));
    return nextFileName.toString();
  }

  private static boolean containsDuplicateFileName(List<File> files, File currentFile) {
    for (final File file : files) {
      if (file.getName().equals(currentFile.getName()) && !file.equals(currentFile))
        return true;
    }
    return false;
  }

  public static List<File> unzip(File zipFile, String outputFolder) throws IOException {
    final byte[] buffer = new byte[1024];
    final List<File> files = new ArrayList<File>();

    final File folder = new File(outputFolder);
    if (!folder.exists()) {
      folder.mkdir();
    }

    final ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
    ZipEntry ze = zis.getNextEntry();

    while (ze != null) {
      final String fileName = ze.getName();
      final File newFile = new File(outputFolder + File.separator + fileName);
      new File(newFile.getParent()).mkdirs();
      final FileOutputStream fos = new FileOutputStream(newFile);

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
}
