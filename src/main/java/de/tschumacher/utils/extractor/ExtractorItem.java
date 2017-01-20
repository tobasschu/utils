package de.tschumacher.utils.extractor;

public abstract class ExtractorItem<T> {
  public abstract String extract(T t);
}
