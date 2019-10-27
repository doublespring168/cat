package org.unidal.web.mvc.lifecycle;

import org.unidal.web.mvc.ActionContext;
import org.unidal.web.mvc.model.entity.ErrorModel;

public interface ErrorHandler {
    void handle(ActionContext<?> context, Throwable cause);

    void initialize(ErrorModel error);
}
