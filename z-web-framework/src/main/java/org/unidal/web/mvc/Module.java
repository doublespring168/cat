package org.unidal.web.mvc;

public interface Module {
    Class<? extends PageHandler<?>>[] getPageHandlers();
}
