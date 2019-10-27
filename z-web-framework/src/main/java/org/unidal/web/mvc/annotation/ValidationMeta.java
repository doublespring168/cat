package org.unidal.web.mvc.annotation;

import org.unidal.web.mvc.Validator;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(METHOD)
public @interface ValidationMeta {
    Class<? extends Validator<?>>[] value();
}
