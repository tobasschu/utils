package de.tschumacher.utils.extractor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Strings;

public class SetExtractor {

  public static <F> Set<String> extract(ExtractorItem<F> extractor, List<F> ts) {
    final Set<String> result = new HashSet<String>();
    if (ts == null)
      return result;

    for (final F t : ts) {
      final String extraction = extractor.extract(t);
      if (!Strings.isNullOrEmpty(extraction)) {
        result.add(extraction);
      }
    }
    return result;
  }

  public static <F> Set<String> extract(MultiExtractorItem<F> extractor, List<F> ts) {
    final Set<String> result = new HashSet<String>();
    if (ts == null)
      return result;

    for (final F t : ts) {
      if (t != null) {
        for (final String id : extractor.extract(t)) {
          if (id != null) {
            result.add(id);
          }
        }
      }
    }

    return result;
  }

}
