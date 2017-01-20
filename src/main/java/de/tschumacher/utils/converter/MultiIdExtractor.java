package de.tschumacher.utils.converter;


public abstract class MultiIdExtractor<T> {
  public abstract String[] extract(T t);
}
