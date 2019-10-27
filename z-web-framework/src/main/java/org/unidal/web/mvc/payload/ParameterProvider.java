package org.unidal.web.mvc.payload;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

public interface ParameterProvider {
    InputStream getFile(String name) throws IOException;

    String getModuleName();

    String[] getParameterNames();

    String getParameter(String name);

    String[] getParameterValues(String name);

    HttpServletRequest getRequest();

    ParameterProvider setRequest(HttpServletRequest request);
}
