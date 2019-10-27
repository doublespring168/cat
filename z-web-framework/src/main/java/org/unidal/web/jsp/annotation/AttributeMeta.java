package org.unidal.web.jsp.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
public @interface AttributeMeta {
    String name() default "";

    String description() default "";

    boolean required() default false;

    boolean rtexprvalue() default true;

    boolean fragment() default false;

}