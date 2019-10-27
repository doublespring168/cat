package org.unidal.web.mvc.lifecycle;

import org.unidal.web.mvc.model.entity.*;

public interface ActionHandlerManager {
    ErrorHandler getErrorHandler(ModuleModel module, ErrorModel error);

    InboundActionHandler getInboundActionHandler(ModuleModel module, InboundActionModel inboundAction);

    OutboundActionHandler getOutboundActionHandler(ModuleModel module, OutboundActionModel outboundAction);

    TransitionHandler getTransitionHandler(ModuleModel module, TransitionModel transition);
}
