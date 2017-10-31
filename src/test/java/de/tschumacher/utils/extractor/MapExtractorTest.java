package de.tschumacher.utils.extractor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MapExtractorTest {


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

    final Map<String, String> convertedMap = MapExtractor.extract(this.extractorItem, inputList);

    assertNotNull(convertedMap);
    assertEquals(inputList.get(0), convertedMap.get(inputList.get(0)));
    assertEquals(inputList.get(1), convertedMap.get(inputList.get(1)));
    assertEquals(inputList.get(2), convertedMap.get(inputList.get(2)));
  }

  @Test
  public void defaultNullTest() {
    final List<String> inputList = null;;

    final Map<String, String> convertedMap = MapExtractor.extract(this.extractorItem, inputList);

    assertNotNull(convertedMap);
    assertTrue(convertedMap.isEmpty());
  }

  @Test
  public void defaultItemNullTest() {
    final List<String> inputList = new ArrayList<String>(Arrays.asList("1", null, "3"));

    final Map<String, String> convertedMap = MapExtractor.extract(this.extractorItem, inputList);

    assertNotNull(convertedMap);
    assertFalse(convertedMap.containsKey(inputList.get(1)));
    assertEquals(inputList.get(0), convertedMap.get(inputList.get(0)));
    assertEquals(inputList.get(2), convertedMap.get(inputList.get(2)));
  }

  @Test
  public void multiTest() {
    final List<Set<String>> inputList =
        new ArrayList<Set<String>>(Arrays.asList(new HashSet<String>(Arrays.asList("1", "2")),
            new HashSet<String>(Arrays.asList("3", "4")),
            new HashSet<String>(Arrays.asList("5", "6"))));

    final Map<String, Set<String>> convertedMap =
        MapExtractor.extract(this.multiExtractorItem, inputList);

    assertNotNull(convertedMap);
    assertEquals(inputList.get(0), convertedMap.get(inputList.get(0).toArray()[0]));
    assertEquals(inputList.get(0), convertedMap.get(inputList.get(0).toArray()[1]));
    assertEquals(inputList.get(1), convertedMap.get(inputList.get(1).toArray()[0]));
    assertEquals(inputList.get(1), convertedMap.get(inputList.get(1).toArray()[1]));
    assertEquals(inputList.get(2), convertedMap.get(inputList.get(2).toArray()[0]));
    assertEquals(inputList.get(2), convertedMap.get(inputList.get(2).toArray()[1]));
  }

  @Test
  public void multiNullTest() {
    final List<Set<String>> inputList = null;

    final Map<String, Set<String>> convertedMap =
        MapExtractor.extract(this.multiExtractorItem, inputList);

    assertNotNull(convertedMap);
    assertTrue(convertedMap.isEmpty());

  }

  @Test
  public void multiFirstLevelNullTest() {
    final List<Set<String>> inputList =
        new ArrayList<Set<String>>(Arrays.asList(new HashSet<String>(Arrays.asList("1", "2")),
            null, new HashSet<String>(Arrays.asList("5", "6"))));

    final Map<String, Set<String>> convertedMap =
        MapExtractor.extract(this.multiExtractorItem, inputList);

    assertNotNull(convertedMap);
    assertFalse(convertedMap.containsKey(inputList.get(1)));
    assertEquals(inputList.get(0), convertedMap.get(inputList.get(0).toArray()[0]));
    assertEquals(inputList.get(0), convertedMap.get(inputList.get(0).toArray()[1]));
    assertEquals(inputList.get(2), convertedMap.get(inputList.get(2).toArray()[0]));
    assertEquals(inputList.get(2), convertedMap.get(inputList.get(2).toArray()[1]));
  }

  @Test
  public void multiItemSecondLevelNullTest() {
    final List<Set<String>> inputList =
        new ArrayList<Set<String>>(Arrays.asList(new HashSet<String>(Arrays.asList("1", "2")),
            new HashSet<String>(Arrays.asList("3", null)),
            new HashSet<String>(Arrays.asList("5", "6"))));

    final Map<String, Set<String>> convertedMap =
        MapExtractor.extract(this.multiExtractorItem, inputList);

    assertNotNull(convertedMap);
    assertFalse(convertedMap.containsKey(null));
    assertEquals(inputList.get(0), convertedMap.get(inputList.get(0).toArray()[0]));
    assertEquals(inputList.get(0), convertedMap.get(inputList.get(0).toArray()[1]));
    assertEquals(inputList.get(1), convertedMap.get(inputList.get(1).toArray()[1]));
    assertEquals(inputList.get(2), convertedMap.get(inputList.get(2).toArray()[0]));
    assertEquals(inputList.get(2), convertedMap.get(inputList.get(2).toArray()[1]));
  }



  @Test
  public void defaultExtractListTest() {
    final List<String> inputList = new ArrayList<String>(Arrays.asList("1", "1", "1"));

    final Map<String, List<String>> convertedMap =
        MapExtractor.extractList(this.extractorItem, inputList);

    assertNotNull(convertedMap);
    assertEquals(1, convertedMap.size());
    assertEquals(3, convertedMap.get(inputList.get(0)).size());
    assertEquals(inputList.get(0), convertedMap.get(inputList.get(0)).get(0));
    assertEquals(inputList.get(1), convertedMap.get(inputList.get(0)).get(1));
    assertEquals(inputList.get(2), convertedMap.get(inputList.get(0)).get(2));
  }


  @Test
  public void multiExtractListTest() {

    final List<Set<String>> inputList =
        new ArrayList<Set<String>>(Arrays.asList(new HashSet<String>(Arrays.asList("1", "2")),
            new HashSet<String>(Arrays.asList("1", "2")),
            new HashSet<String>(Arrays.asList("1", "2"))));

    final Map<String, List<Set<String>>> convertedMap =
        MapExtractor.extractList(this.multiExtractorItem, inputList);

    assertNotNull(convertedMap);
    assertEquals(2, convertedMap.size());
    assertEquals(3, convertedMap.get("1").size());
    assertEquals(3, convertedMap.get("2").size());
  }
}
