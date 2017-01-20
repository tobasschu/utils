package de.tschumacher.utils.converter;

public abstract class IdExtractor<T> {
  public abstract String extract(T t);
}
