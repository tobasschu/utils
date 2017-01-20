package de.tschumacher.utils.converter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapConverter {
  public static <F> Map<String, F> convert(final IdExtractor<F> extractor, final List<F> fs) {
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

  public static <F> Map<String, F> convertMulti(final MultiIdExtractor<F> extractor,
      final List<F> fs) {
    final Map<String, F> result = new HashMap<String, F>();
    if (fs != null) {
      for (final F f : fs) {
        final String[] extractedIds = extractor.extract(f);
        if (extractedIds != null) {
          for (final String id : extractedIds) {
            result.put(id, f);
          }
        }
      }
    }
    return result;
  }
}
