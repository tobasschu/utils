package de.tschumacher.utils;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ZipFileUtilsTest {
  @Test
  public void testZip() throws IOException {
    final List<File> files = new ArrayList<File>();
    final File file1 = FileUtils.createFileWithContent("filname", "content");
    files.add(file1);
    final File file2 = FileUtils.createFileWithContent("filname2", "content");
    files.add(file2);

    final File zipFile = ZipFileUtils.zip(files, "zipFile");

    assertTrue(zipFile.exists());
    zipFile.deleteOnExit();
    file1.deleteOnExit();
    file2.deleteOnExit();
  }

  @Test
  public void testZipDuplicates() throws IOException {
    final List<File> files = new ArrayList<File>();
    final File file1 = FileUtils.createFileWithContent("test/filname", "content");
    files.add(file1);
    final File file2 = FileUtils.createFileWithContent("test1/filname", "content");
    files.add(file2);
    final File file3 = FileUtils.createFileWithContent("test2/filname", "content");
    files.add(file3);
    final File file4 = FileUtils.createFileWithContent("test2/filname_1", "content");
    files.add(file4);

    final File zipFile = ZipFileUtils.zip(files, "zipFile.zip");

    assertTrue(zipFile.exists());
    zipFile.deleteOnExit();

    delete(file1);
    delete(file2);
    delete(file3);
    delete(file4);
  }

  private void delete(final File file1) {
    file1.delete();
    if (file1.getParentFile().list().length == 0) {
      file1.getParentFile().delete();
    }
  }
}
