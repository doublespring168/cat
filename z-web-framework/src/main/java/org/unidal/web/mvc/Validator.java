package org.unidal.web.mvc;

public interface Validator<T extends ActionContext<?>> {
    void validate(T context) throws Exception;
}
