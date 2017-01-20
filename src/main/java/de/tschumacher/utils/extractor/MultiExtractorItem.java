package de.tschumacher.utils.extractor;

import java.util.Set;

public abstract class MultiExtractorItem<T> {
  public abstract Set<String> extract(T t);
}
