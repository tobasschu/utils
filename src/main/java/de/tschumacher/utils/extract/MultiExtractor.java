package de.tschumacher.utils.extract;

import java.util.Set;

public abstract class MultiExtractor<T> {
  public abstract Set<String> extract(T t);
}
