package org.unidal.web.mvc.model.entity;

import org.unidal.web.mvc.model.BaseEntity;
import org.unidal.web.mvc.model.IVisitor;

import java.util.LinkedHashMap;
import java.util.Map;

public class DomainModel extends BaseEntity<DomainModel> {
    private Map<String, ModuleModel> m_modules = new LinkedHashMap<String, ModuleModel>();

    public DomainModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitDomain(this);
    }

    @Override
    public void mergeAttributes(DomainModel other) {
    }

    public DomainModel addModule(ModuleModel module) {
        m_modules.put(module.getModuleName(), module);
        return this;
    }

    public ModuleModel findModule(String moduleName) {
        return m_modules.get(moduleName);
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_modules == null ? 0 : m_modules.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DomainModel) {
            DomainModel _o = (DomainModel) obj;

            return equals(m_modules, _o.getModules());
        }

        return false;
    }

    public Map<String, ModuleModel> getModules() {
        return m_modules;
    }

    public ModuleModel removeModule(String moduleName) {
        return m_modules.remove(moduleName);
    }

}
