package org.unidal.web.mvc;

import org.unidal.web.lifecycle.UrlMapping;
import org.unidal.web.mvc.payload.ParameterProvider;

import java.util.List;

public interface PayloadProvider<S extends Page, T extends Action> {
    void register(Class<?> payloadClass);

    List<ErrorObject> process(UrlMapping mapping, ParameterProvider parameterProvider, ActionPayload<S, T> payload);
}
