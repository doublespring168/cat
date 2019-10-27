package org.unidal.web.mvc.annotation;

import org.unidal.web.lifecycle.ActionResolver;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(TYPE)
public @interface ModuleMeta {
    String name();

    String defaultInboundAction() default "";

    String defaultTransition() default "";

    String defaultErrorAction() default "";

    Class<? extends ActionResolver> actionResolver() default ActionResolver.class;
}
