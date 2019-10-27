package org.unidal.converter.basic;

import org.unidal.converter.Converter;
import org.unidal.converter.ConverterException;
import org.unidal.converter.TypeUtil;

import java.lang.reflect.Type;

public class FloatConverter implements Converter<Float> {

    public boolean canConvert(Type fromType, Type targetType) {
        return TypeUtil.isTypeSupported(fromType, Number.class, Boolean.TYPE, Boolean.class, String.class, Enum.class);
    }

    public Type getTargetType() {
        return Float.class;
    }

    public Float convert(Object from, Type targetType) throws ConverterException {
        if (from instanceof Number) {
            return ((Number) from).floatValue();
        } else if (from instanceof Boolean) {
            return ((Boolean) from).booleanValue() ? Float.valueOf(1) : 0;
        } else if (from instanceof Enum) {
            return Float.valueOf(((Enum<?>) from).ordinal());
        } else {
            try {
                return Float.valueOf(from.toString());
            } catch (NumberFormatException e) {
                throw new ConverterException(e);
            }
        }
    }
}
