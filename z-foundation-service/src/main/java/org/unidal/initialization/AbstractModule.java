package org.unidal.initialization;

public abstract class AbstractModule implements Module {
    private boolean m_initialized;

    @Override
    public void initialize(ModuleContext ctx) throws Exception {
        execute(ctx);
    }

    protected abstract void execute(ModuleContext ctx) throws Exception;

    @Override
    public boolean isInitialized() {
        return m_initialized;
    }

    @Override
    public void setInitialized(boolean initialized) {
        m_initialized = initialized;
    }

    protected void setup(ModuleContext ctx) throws Exception {
        // no nothing by default
    }
}