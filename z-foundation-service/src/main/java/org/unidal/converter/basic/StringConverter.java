package org.unidal.converter.basic;

import org.unidal.converter.Converter;
import org.unidal.converter.ConverterException;

import java.lang.reflect.Type;

public class StringConverter implements Converter<String> {
    public boolean canConvert(Type fromType, Type targetType) {
        return true;
    }

    public Type getTargetType() {
        return String.class;
    }

    public String convert(Object from, Type targetType) throws ConverterException {
        return from.toString();
    }
}
