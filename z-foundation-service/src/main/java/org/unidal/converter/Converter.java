package org.unidal.converter;

import java.lang.reflect.Type;

public interface Converter<T> {
    boolean canConvert(Type fromType, Type targetType);

    Type getTargetType();

    T convert(Object from, Type targetType) throws ConverterException;
}
