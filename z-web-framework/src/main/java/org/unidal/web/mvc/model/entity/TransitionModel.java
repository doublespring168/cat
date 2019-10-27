package org.unidal.web.mvc.model.entity;

import org.unidal.web.mvc.model.BaseEntity;
import org.unidal.web.mvc.model.IVisitor;

import static org.unidal.web.mvc.model.Constants.ATTR_TRANSITIONNAME;
import static org.unidal.web.mvc.model.Constants.ENTITY_TRANSITION;

public class TransitionModel extends BaseEntity<TransitionModel> {
    private String m_transitionName;

    private Object m_moduleInstance;

    private java.lang.reflect.Method m_method;

    public TransitionModel() {
    }

    public TransitionModel(String transitionName) {
        m_transitionName = transitionName;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitTransition(this);
    }

    @Override
    public void mergeAttributes(TransitionModel other) {
        assertAttributeEquals(other, ENTITY_TRANSITION, ATTR_TRANSITIONNAME, m_transitionName, other.getTransitionName());

    }

    public java.lang.reflect.Method getMethod() {
        return m_method;
    }

    public TransitionModel setMethod(java.lang.reflect.Method method) {
        m_method = method;
        return this;
    }

    public Object getModuleInstance() {
        return m_moduleInstance;
    }

    public TransitionModel setModuleInstance(Object moduleInstance) {
        m_moduleInstance = moduleInstance;
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_transitionName == null ? 0 : m_transitionName.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TransitionModel) {
            TransitionModel _o = (TransitionModel) obj;

            return equals(m_transitionName, _o.getTransitionName());
        }

        return false;
    }

    public String getTransitionName() {
        return m_transitionName;
    }

    public TransitionModel setTransitionName(String transitionName) {
        m_transitionName = transitionName;
        return this;
    }

}
