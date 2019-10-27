package org.unidal.web.mvc.model;

import org.unidal.web.mvc.model.entity.*;

public interface IVisitor {

    void visitDomain(DomainModel domain);

    void visitError(ErrorModel error);

    void visitField(PayloadFieldModel field);

    void visitInbound(InboundActionModel inbound);

    void visitModule(ModuleModel module);

    void visitObject(PayloadObjectModel object);

    void visitOutbound(OutboundActionModel outbound);

    void visitPath(PayloadPathModel path);

    void visitPayload(PayloadModel payload);

    void visitTransition(TransitionModel transition);
}
