package org.unidal.web.mvc.view.model;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ModelHandler {
    void handle(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException;
}
