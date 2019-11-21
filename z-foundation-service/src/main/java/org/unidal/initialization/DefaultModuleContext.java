package org.unidal.initialization;

import com.doublespring.common.U;
import com.doublespring.log.LogUtil;
import org.codehaus.plexus.PlexusContainer;
import org.codehaus.plexus.component.repository.exception.ComponentLifecycleException;
import org.codehaus.plexus.component.repository.exception.ComponentLookupException;
import org.codehaus.plexus.context.Context;
import org.codehaus.plexus.context.ContextException;
import org.codehaus.plexus.logging.Logger;
import org.codehaus.plexus.logging.LoggerManager;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.Contextualizable;
import org.unidal.lookup.annotation.Named;

import java.util.HashMap;
import java.util.Map;

@Named(type = ModuleContext.class)
public class DefaultModuleContext implements ModuleContext, Contextualizable {
    private PlexusContainer m_container;

    private Map<String, Object> m_attributes = new HashMap<String, Object>();

    private Logger m_logger;

    public DefaultModuleContext() {
    }

    public DefaultModuleContext(PlexusContainer container) {

        LogUtil.info("实例化 DefaultModuleContext");
        m_container = container;

        setup();
    }

    private void setup() {
        try {

            LogUtil.info("初始化 LoggerManager");
            LoggerManager loggerManager = m_container.lookup(LoggerManager.class);

            LogUtil.info("初始化 m_logger");
            m_logger = loggerManager.getLoggerForComponent(PlexusContainer.class.getName());
        } catch (Exception e) {
            throw new RuntimeException("Unable to get instance of Logger, "
                    + "please make sure the environment was setup correctly!", e);
        }
    }

    @Override
    public void contextualize(Context context) throws ContextException {
        m_container = (PlexusContainer) context.get("plexus");

        setup();
    }

    @Override
    public <T> T getAttribute(String name) {
        return getAttribute(name, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getAttribute(String name, T defaultValue) {
        Object value = m_attributes.get(name);

        if (value != null) {
            return (T) value;
        } else {
            return defaultValue;
        }
    }

    @Override
    public void info(String message) {
        LogUtil.info(message);
    }

    @Override
    public void warn(String message) {
        m_logger.warn(message);
    }

    @Override
    public void error(String message) {
        m_logger.error(message);
    }

    @Override
    public void error(String message, Throwable e) {
        LogUtil.info("异常错误", U.format("message", message, "exception", U.toString(e)));
        m_logger.error(message, e);
    }

    @Override
    public <T> T lookup(Class<T> role) {
        return lookup(role, null);
    }

    @Override
    public <T> T lookup(Class<T> role, String roleHint) {
        try {
            LogUtil.info("查找依赖Bean", U.format("role", role.getName(), "roleHint", roleHint));
            return m_container.lookup(role, roleHint);
        } catch (ComponentLookupException e) {
            throw new RuntimeException("Unable to get component: " + role + ".", e);
        }
    }

    @Override
    public void release(Object component) {
        try {
            m_container.release(component);
        } catch (ComponentLifecycleException e) {
            throw new RuntimeException("Unable to release component: " + component + ".", e);
        }
    }

    @Override
    public void setAttribute(String name, Object value) {
        m_attributes.put(name, value);
    }

    @Override
    public Module[] getModules(String... names) {

        LogUtil.info("加载模块", U.format("names", U.toString(names)));

        Module[] modules = new Module[names.length];
        int index = 0;

        for (String name : names) {
            modules[index++] = lookup(Module.class, name);
        }

        return modules;
    }

    public PlexusContainer getContainer() {
        return m_container;
    }


    public PlexusContainer getM_container() {
        return m_container;
    }

    public Map<String, Object> getM_attributes() {
        return m_attributes;
    }

    public Logger getM_logger() {
        return m_logger;
    }
}
