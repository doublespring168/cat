package org.unidal.lookup.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({TYPE})
public @interface Named {
    String PER_LOOKUP = "per-lookup";
    String ENUM = "enum";

    Class<?> type() default Default.class;

    String value() default "";

    String instantiationStrategy() default "";

    final class Default {
    }
}
