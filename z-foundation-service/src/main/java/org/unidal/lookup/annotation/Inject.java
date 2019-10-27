package org.unidal.lookup.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({FIELD})
public @interface Inject {
    Class<?> type() default Default.class;

    String[] value() default {};

    @Deprecated
    String instantiationStrategy() default "";

    final class Default {
    }
}
