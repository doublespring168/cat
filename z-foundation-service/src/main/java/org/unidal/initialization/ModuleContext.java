package org.unidal.initialization;

public interface ModuleContext {
    <T> T getAttribute(String name);

    <T> T getAttribute(String name, T defaultValue);

    void info(String message);

    void warn(String message);

    void error(String message);

    void error(String message, Throwable t);

    <T> T lookup(Class<T> role);

    <T> T lookup(Class<T> role, String roleHint);

    void release(Object component);

    void setAttribute(String name, Object value);

    Module[] getModules(String... modules);
}
