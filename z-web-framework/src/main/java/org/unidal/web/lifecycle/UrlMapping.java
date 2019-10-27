package org.unidal.web.lifecycle;

public interface UrlMapping {
    String getAction();

    String getContextPath();

    String getModule();

    void setModule(String module);

    String getPathInfo();

    String getQueryString();

    String getServletPath();
}