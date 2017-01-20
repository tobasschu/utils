package de.tschumacher.utils.extract;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.common.base.Strings;

public class ExtractorManager {

  public static <F> Set<String> extract(Extractor<F> extractor, List<F> ts) {
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

  public static <F> Set<String> extract(MultiExtractor<F> extractor, List<F> ts) {
    final Set<String> result = new HashSet<String>();
    if (ts == null)
      return result;

    for (final F t : ts) {
      result.addAll(extractor.extract(t));
    }
    return result;
  }

}
