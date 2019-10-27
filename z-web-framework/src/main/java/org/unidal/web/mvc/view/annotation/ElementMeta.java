package org.unidal.web.mvc.view.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ElementMeta {
    String value() default ""; // auto detect

    String format() default "";

    boolean multiple() default false;

    String names() default "";
}
