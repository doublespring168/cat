package org.unidal.web.mvc.annotation;

import org.unidal.web.mvc.PageHandler;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
public @interface ModulePagesMeta {
    Class<? extends PageHandler<?>>[] value();
}
