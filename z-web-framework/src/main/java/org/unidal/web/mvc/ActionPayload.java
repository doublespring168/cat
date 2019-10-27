package org.unidal.web.mvc;

public interface ActionPayload<S extends Page, T extends Action> {
    T getAction();

    S getPage();

    void setPage(String page);

    void validate(ActionContext<?> ctx);
}
