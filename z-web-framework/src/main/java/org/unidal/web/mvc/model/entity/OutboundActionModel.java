package org.unidal.web.mvc.model.entity;

import org.unidal.web.mvc.model.BaseEntity;
import org.unidal.web.mvc.model.IVisitor;

import static org.unidal.web.mvc.model.Constants.ATTR_ACTIONNAME;
import static org.unidal.web.mvc.model.Constants.ENTITY_OUTBOUND;

public class OutboundActionModel extends BaseEntity<OutboundActionModel> {
    private String m_actionName;

    private Object m_moduleInstance;

    private java.lang.reflect.Method m_method;

    public OutboundActionModel() {
    }

    public OutboundActionModel(String actionName) {
        m_actionName = actionName;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitOutbound(this);
    }

    @Override
    public void mergeAttributes(OutboundActionModel other) {
        assertAttributeEquals(other, ENTITY_OUTBOUND, ATTR_ACTIONNAME, m_actionName, other.getActionName());

    }

    public java.lang.reflect.Method getMethod() {
        return m_method;
    }

    public OutboundActionModel setMethod(java.lang.reflect.Method method) {
        m_method = method;
        return this;
    }

    public Object getModuleInstance() {
        return m_moduleInstance;
    }

    public OutboundActionModel setModuleInstance(Object moduleInstance) {
        m_moduleInstance = moduleInstance;
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_actionName == null ? 0 : m_actionName.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof OutboundActionModel) {
            OutboundActionModel _o = (OutboundActionModel) obj;

            return equals(m_actionName, _o.getActionName());
        }

        return false;
    }

    public String getActionName() {
        return m_actionName;
    }

    public OutboundActionModel setActionName(String actionName) {
        m_actionName = actionName;
        return this;
    }

}
