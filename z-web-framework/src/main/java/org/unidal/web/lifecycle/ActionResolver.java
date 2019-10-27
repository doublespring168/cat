package org.unidal.web.lifecycle;

import org.unidal.web.mvc.payload.ParameterProvider;

public interface ActionResolver {
    UrlMapping parseUrl(ParameterProvider provider);

    String buildUrl(ParameterProvider provider, UrlMapping mapping);
}
