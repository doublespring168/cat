package org.unidal.web.mvc.payload.annotation;

import org.unidal.web.mvc.Action;
import org.unidal.web.mvc.Page;
import org.unidal.web.mvc.PayloadProvider;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
public @interface PayloadProviderMeta {
    Class<? extends PayloadProvider<? extends Page, ? extends Action>> value();
}
