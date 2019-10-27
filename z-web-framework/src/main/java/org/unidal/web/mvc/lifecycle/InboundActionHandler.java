package org.unidal.web.mvc.lifecycle;

import org.unidal.web.mvc.ActionContext;
import org.unidal.web.mvc.ActionException;
import org.unidal.web.mvc.model.entity.InboundActionModel;

public interface InboundActionHandler {
    void handle(ActionContext<?> context) throws ActionException;

    void initialize(InboundActionModel inboundAction);

    void preparePayload(ActionContext<?> ctx);
}
