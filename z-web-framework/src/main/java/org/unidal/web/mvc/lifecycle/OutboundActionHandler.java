package org.unidal.web.mvc.lifecycle;

import org.unidal.web.mvc.ActionContext;
import org.unidal.web.mvc.ActionException;
import org.unidal.web.mvc.model.entity.OutboundActionModel;

public interface OutboundActionHandler {
    void handle(ActionContext<?> context) throws ActionException;

    void initialize(OutboundActionModel outboundAction);
}
