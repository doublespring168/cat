package org.unidal.initialization;

public interface Module {
    Module[] getDependencies(ModuleContext ctx);

    void initialize(ModuleContext ctx) throws Exception;

    boolean isInitialized();

    void setInitialized(boolean initialized);
}
