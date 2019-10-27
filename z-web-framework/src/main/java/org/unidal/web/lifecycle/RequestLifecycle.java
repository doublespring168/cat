package org.unidal.web.lifecycle;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface RequestLifecycle {
    void handle(final HttpServletRequest request, final HttpServletResponse response) throws IOException;

    void setServletContext(ServletContext servletContext);
}
