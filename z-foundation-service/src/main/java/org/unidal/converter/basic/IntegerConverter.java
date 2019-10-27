package org.unidal.converter.basic;

import org.unidal.converter.Converter;
import org.unidal.converter.ConverterException;
import org.unidal.converter.TypeUtil;

import java.lang.reflect.Type;

public class IntegerConverter implements Converter<Integer> {

    public boolean canConvert(Type fromType, Type targetType) {
        return TypeUtil.isTypeSupported(fromType, Number.class, Boolean.TYPE, Boolean.class, String.class, Enum.class);
    }

    public Type getTargetType() {
        return Integer.class;
    }

    public Integer convert(Object from, Type targetType) throws ConverterException {
        if (from instanceof Number) {
            return ((Number) from).intValue();
        } else if (from instanceof Boolean) {
            return ((Boolean) from).booleanValue() ? Integer.valueOf(1) : 0;
        } else if (from instanceof Enum) {
            return Integer.valueOf(((Enum<?>) from).ordinal());
        } else {
            try {
                return Integer.valueOf(from.toString().trim());
            } catch (NumberFormatException e) {
                throw new ConverterException(e);
            }
        }
    }
}
