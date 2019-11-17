package org.unidal.web;

import com.doublespring.common.U;
import org.codehaus.plexus.DefaultPlexusContainer;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.repository.exception.ComponentLifecycleException;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.logging.Logger;
import org.unidal.lookup.ContainerLoader;
import org.unidal.lookup.LookupException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public abstract class AbstractContainerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private PlexusContainer m_container;

    private Logger m_logger;

    protected PlexusContainer getContainer() {
        return m_container;
    }

    // For test purpose
    public AbstractContainerServlet setContainer(PlexusContainer container) {
        m_container = container;
        return this;
    }

    protected Logger getLogger() {
        return m_logger;
    }

    protected <T> boolean hasComponent(Class<T> role) {
        return hasComponent(role, null);
    }

    protected <T> boolean hasComponent(Class<T> role, Object roleHint) {
        return m_container.hasComponent(role.getName(), roleHint == null ? "default" : roleHint.toString());
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        m_logger.info("初始化 AbstractContainerServlet");
        m_logger.info(U.format("config", U.toJSS(config)).get());

        m_logger.info("初始化 HttpServlet");
        super.init(config);


        try {
            if (m_container == null) {
                m_logger.info("初始化 PlexusContainer");
                m_container = ContainerLoader.getDefaultContainer();
            } else {
                m_logger.info("已存在 PlexusContainer,不需初始化");
            }

            m_logger = ((DefaultPlexusContainer) m_container).getLoggerManager().getLoggerForComponent(
                    getClass().getName());

            initComponents(config);
        } catch (Exception e) {
            if (m_logger != null) {
                m_logger.error("Servlet initializing failed. " + e, e);
            } else {
                System.out.println("Servlet initializing failed. " + e);
                e.printStackTrace(System.out);
            }

            throw new ServletException("Servlet initializing failed. " + e, e);
        }
    }

    protected abstract void initComponents(ServletConfig config) throws Exception;

    protected <T> T lookup(Class<T> role) throws LookupException {
        return lookup(role, null);
    }

    protected <T> T lookup(Class<T> role, String roleHint) throws LookupException {
        try {
            return m_container.lookup(role, roleHint == null ? "default" : roleHint);
        } catch (ComponentLookupException e) {
            throw new LookupException("Component(" + role.getName() + ") lookup failure. details: " + e.getMessage(), e);
        }
    }

    protected void release(Object component) throws LookupException {
        if (component != null) {
            try {
                m_container.release(component);
            } catch (ComponentLifecycleException e) {
                throw new LookupException("Can't release component: " + component, e);
            }
        }
    }
}
