package org.unidal.initialization;

public interface ModuleInitializer {
    void execute(ModuleContext ctx);

    void execute(ModuleContext ctx, Module... modules);
}
