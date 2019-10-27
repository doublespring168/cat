package org.unidal.test.jetty;

import org.mortbay.jetty.servlet.DefaultServlet;
import org.mortbay.resource.Resource;

import java.io.IOException;

public class WebModuleServlet extends DefaultServlet {
    private static final long serialVersionUID = 1L;

    private WebModuleResource m_resource;

    public WebModuleServlet(WebModuleResource resource) {
        m_resource = resource;
    }

    @Override
    public Resource getResource(String pathInContext) {
        try {
            return m_resource.addPath(pathInContext);
        } catch (IOException e) {
            // ignore it
        }

        return super.getResource(pathInContext);
    }
}
