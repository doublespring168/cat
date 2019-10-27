package org.unidal.web.mvc.view.model;

import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;
import org.unidal.lookup.annotation.Inject;
import org.unidal.web.mvc.ActionContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompositeModelHandler implements ModelHandler, Initializable {
    @Inject
    private List<ModelHandler> m_handlers;

    @Override
    public void handle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        ActionContext<?> ctx = (ActionContext<?>) req.getAttribute("ctx");

        for (ModelHandler handler : m_handlers) {
            handler.handle(req, res);

            if (ctx.isProcessStopped()) {
                break;
            }
        }
    }

    @Override
    public void initialize() throws InitializationException {
        // to work around a performance issue within Plexus framework
        ArrayList<ModelHandler> handlers = new ArrayList<ModelHandler>();

        if (m_handlers != null) {
            handlers.addAll(m_handlers);
        }

        m_handlers = handlers;
    }
}
