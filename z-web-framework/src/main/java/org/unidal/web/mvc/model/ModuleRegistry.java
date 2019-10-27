package org.unidal.web.mvc.model;

import org.codehaus.plexus.personality.plexus.lifecycle.phase.Initializable;
import org.codehaus.plexus.personality.plexus.lifecycle.phase.InitializationException;
import org.unidal.lookup.ContainerHolder;
import org.unidal.web.mvc.Module;

import java.util.List;

public class ModuleRegistry extends ContainerHolder implements Initializable {
    private String m_defaultModuleName;

    private Module m_defaultModule;

    private List<Module> m_modules;

    public Module getDefaultModule() {
        return m_defaultModule;
    }

    public void setDefaultModule(String defaultModuleName) {
        m_defaultModuleName = defaultModuleName;
    }

    public List<Module> getModules() {
        return m_modules;
    }

    @Override
    public void initialize() throws InitializationException {
        if (m_defaultModuleName != null) {
            m_defaultModule = lookup(Module.class, m_defaultModuleName);
        }

        m_modules = lookupList(Module.class);
    }
}
