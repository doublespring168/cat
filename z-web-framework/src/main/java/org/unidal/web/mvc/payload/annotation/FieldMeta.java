package org.unidal.web.mvc.payload.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
public @interface FieldMeta {
    String NOT_SPECIFIED = "NOT_SPECIFIED";

    String defaultValue() default NOT_SPECIFIED;

    String format() default "";

    boolean file() default false;

    String value();
}
