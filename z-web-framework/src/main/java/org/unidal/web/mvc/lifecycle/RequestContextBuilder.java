package org.unidal.web.mvc.lifecycle;

import javax.servlet.http.HttpServletRequest;

public interface RequestContextBuilder {

    RequestContext build(HttpServletRequest request);

    void reset(RequestContext requestContext);
}
