package de.tschumacher.utils.extractor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MapExtractor {
  public static <F> Map<String, F> extract(final ExtractorItem<F> extractor, final List<F> fs) {
    final Map<String, F> result = new HashMap<String, F>();
    if (fs != null) {
      for (final F f : fs) {
        final String key = extractor.extract(f);
        if (key != null) {
          result.put(key, f);
        }
      }
    }
    return result;
  }

  public static <F> Map<String, F> extract(final MultiExtractorItem<F> extractor, final List<F> fs) {
    final Map<String, F> result = new HashMap<String, F>();
    if (fs != null) {
      for (final F f : fs) {
        final Set<String> extractedIds = extractor.extract(f);
        if (extractedIds != null) {
          for (final String id : extractedIds) {
            if (id != null) {
              result.put(id, f);
            }
          }
        }
      }
    }
    return result;
  }

  public static <F> Map<String, List<F>> extractList(final ExtractorItem<F> extractor,
      final List<F> fs) {
    final Map<String, List<F>> result = new HashMap<String, List<F>>();
    if (fs != null) {
      for (final F f : fs) {
        final String key = extractor.extract(f);
        if (key != null) {
          if (result.containsKey(key) && result.get(key) != null) {
            result.get(key).add(f);
          } else {
            result.put(key, new ArrayList<F>(Arrays.asList(f)));
          }
        }
      }
    }
    return result;
  }


  public static <F> Map<String, List<F>> extractList(final MultiExtractorItem<F> extractor,
      final List<F> fs) {
    final Map<String, List<F>> result = new HashMap<String, List<F>>();
    if (fs != null) {
      for (final F f : fs) {
        final Set<String> extractedIds = extractor.extract(f);
        if (extractedIds != null) {
          for (final String id : extractedIds) {
            if (id != null) {
              if (result.containsKey(id) && result.get(id) != null) {
                result.get(id).add(f);
              } else {
                result.put(id, new ArrayList<F>(Arrays.asList(f)));
              }
            }
          }
        }
      }
    }
    return result;
  }
}
