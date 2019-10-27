package org.unidal.web.mvc;

import javax.servlet.ServletException;
import java.io.IOException;

public interface PageHandler<T extends ActionContext<?>> {
    void handleInbound(T ctx) throws ServletException, IOException;

    void handleOutbound(T ctx) throws ServletException, IOException;
}
