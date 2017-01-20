package de.tschumacher.utils.extractor;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SetExtractorTest {


  private ExtractorItem<String> extractorItem;
  private MultiExtractorItem<Set<String>> multiExtractorItem;

  @Before
  public void setUp() {
    this.extractorItem = new ExtractorItem<String>() {
      @Override
      public String extract(String t) {
        return t;
      }
    };
    this.multiExtractorItem = new MultiExtractorItem<Set<String>>() {
      @Override
      public Set<String> extract(Set<String> items) {
        if (items == null)
          return null;
        return new HashSet<String>(items);
      }
    };
  }

  @After
  public void afterTest() {

  }

  @Test
  public void defaultTest() {
    final List<String> inputList = new ArrayList<String>(Arrays.asList("1", "2", "3"));

    final Set<String> convertedSet = SetExtractor.extract(this.extractorItem, inputList);

    assertNotNull(convertedSet);
    assertTrue(convertedSet.contains(inputList.get(0)));
    assertTrue(convertedSet.contains(inputList.get(1)));
    assertTrue(convertedSet.contains(inputList.get(2)));
  }

  @Test
  public void defaultNullTest() {
    final List<String> inputList = null;

    final Set<String> convertedSet = SetExtractor.extract(this.extractorItem, inputList);

    assertNotNull(convertedSet);
    assertTrue(convertedSet.isEmpty());
  }

  @Test
  public void defaultFirstLevelNullTest() {
    final List<String> inputList = new ArrayList<String>(Arrays.asList("1", null, "3"));

    final Set<String> convertedSet = SetExtractor.extract(this.extractorItem, inputList);

    assertNotNull(convertedSet);
    assertTrue(convertedSet.contains(inputList.get(0)));
    assertFalse(convertedSet.contains(inputList.get(1)));
    assertTrue(convertedSet.contains(inputList.get(2)));
  }

  @Test
  public void multiTest() {

    final List<Set<String>> inputList =
        new ArrayList<Set<String>>(Arrays.asList(new HashSet<String>(Arrays.asList("1", "2")),
            new HashSet<String>(Arrays.asList("3", "4")),
            new HashSet<String>(Arrays.asList("5", "6"))));

    final Set<String> convertedSet = SetExtractor.extract(this.multiExtractorItem, inputList);

    assertNotNull(convertedSet);
    assertTrue(convertedSet.contains(inputList.get(0).toArray()[0]));
    assertTrue(convertedSet.contains(inputList.get(0).toArray()[1]));
    assertTrue(convertedSet.contains(inputList.get(1).toArray()[0]));
    assertTrue(convertedSet.contains(inputList.get(1).toArray()[1]));
    assertTrue(convertedSet.contains(inputList.get(2).toArray()[0]));
    assertTrue(convertedSet.contains(inputList.get(2).toArray()[1]));
  }

  @Test
  public void multiNullTest() {

    final List<Set<String>> inputList = null;

    final Set<String> convertedSet = SetExtractor.extract(this.multiExtractorItem, inputList);

    assertNotNull(convertedSet);
    assertTrue(convertedSet.isEmpty());
  }

  @Test
  public void multiFirstLevelNullTest() {

    final List<Set<String>> inputList =
        new ArrayList<Set<String>>(Arrays.asList(new HashSet<String>(Arrays.asList("1", "2")),
            null, new HashSet<String>(Arrays.asList("5", "6"))));

    final Set<String> convertedSet = SetExtractor.extract(this.multiExtractorItem, inputList);

    assertNotNull(convertedSet);
    assertFalse(convertedSet.contains(null));
    assertTrue(convertedSet.contains(inputList.get(0).toArray()[0]));
    assertTrue(convertedSet.contains(inputList.get(0).toArray()[1]));
    assertTrue(convertedSet.contains(inputList.get(2).toArray()[0]));
    assertTrue(convertedSet.contains(inputList.get(2).toArray()[1]));
  }

  @Test
  public void multiSecondLevelNullTest() {

    final List<Set<String>> inputList =
        new ArrayList<Set<String>>(Arrays.asList(new HashSet<String>(Arrays.asList("1", "2")),
            new HashSet<String>(Arrays.asList("3", null)),
            new HashSet<String>(Arrays.asList("5", "6"))));

    final Set<String> convertedSet = SetExtractor.extract(this.multiExtractorItem, inputList);

    assertNotNull(convertedSet);
    assertFalse(convertedSet.contains(null));
    assertTrue(convertedSet.contains(inputList.get(0).toArray()[0]));
    assertTrue(convertedSet.contains(inputList.get(0).toArray()[1]));
    assertTrue(convertedSet.contains(inputList.get(1).toArray()[1]));
    assertTrue(convertedSet.contains(inputList.get(2).toArray()[0]));
    assertTrue(convertedSet.contains(inputList.get(2).toArray()[1]));
  }
}
