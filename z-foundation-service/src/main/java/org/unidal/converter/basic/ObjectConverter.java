package org.unidal.converter.basic;

import org.unidal.converter.Converter;
import org.unidal.converter.ConverterException;

import java.lang.reflect.Type;

public class ObjectConverter implements Converter<Object> {
    public boolean canConvert(Type fromType, Type targetType) {
        return true;
    }

    public Type getTargetType() {
        return Object.class;
    }

    public Object convert(Object from, Type targetType) throws ConverterException {
        return from;
    }
}
