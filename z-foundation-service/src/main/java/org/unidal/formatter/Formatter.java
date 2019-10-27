package org.unidal.formatter;

public interface Formatter<T> {
    String format(String format, T object) throws FormatterException;

    T parse(String format, String text) throws FormatterException;
}
