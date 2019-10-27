package org.unidal.web.mvc.model.entity;

import org.unidal.web.mvc.model.BaseEntity;
import org.unidal.web.mvc.model.IVisitor;

import java.util.ArrayList;
import java.util.List;

import static org.unidal.web.mvc.model.Constants.ATTR_ACTIONNAME;
import static org.unidal.web.mvc.model.Constants.ENTITY_INBOUND;

public class InboundActionModel extends BaseEntity<InboundActionModel> {
    private String m_actionName;

    private String m_transitionName;

    private String m_errorActionName;

    private String[] m_preActionNames;

    private Object m_moduleInstance;

    private java.lang.reflect.Method m_actionMethod;

    private java.lang.reflect.Method m_transitionMethod;

    private java.lang.reflect.Method m_errorMethod;

    private Class<?> m_contextClass;

    private Class<?> m_payloadClass;

    private List<Class<?>> m_validationClasses = new ArrayList<Class<?>>();

    public InboundActionModel() {
    }

    public InboundActionModel(String actionName) {
        m_actionName = actionName;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitInbound(this);
    }

    @Override
    public void mergeAttributes(InboundActionModel other) {
        assertAttributeEquals(other, ENTITY_INBOUND, ATTR_ACTIONNAME, m_actionName, other.getActionName());

        if (other.getTransitionName() != null) {
            m_transitionName = other.getTransitionName();
        }

        if (other.getErrorActionName() != null) {
            m_errorActionName = other.getErrorActionName();
        }

        if (other.getPreActionNames() != null) {
            m_preActionNames = other.getPreActionNames();
        }
    }

    public String getTransitionName() {
        return m_transitionName;
    }

    public String getErrorActionName() {
        return m_errorActionName;
    }

    public String[] getPreActionNames() {
        return m_preActionNames;
    }

    public InboundActionModel setPreActionNames(String[] preActionNames) {
        m_preActionNames = preActionNames;
        return this;
    }

    public InboundActionModel setErrorActionName(String errorActionName) {
        m_errorActionName = errorActionName;
        return this;
    }

    public InboundActionModel setTransitionName(String transitionName) {
        m_transitionName = transitionName;
        return this;
    }

    public InboundActionModel addValidationClass(Class<?> validationClass) {
        m_validationClasses.add(validationClass);
        return this;
    }

    public java.lang.reflect.Method getActionMethod() {
        return m_actionMethod;
    }

    public InboundActionModel setActionMethod(java.lang.reflect.Method actionMethod) {
        m_actionMethod = actionMethod;
        return this;
    }

    public Class<?> getContextClass() {
        return m_contextClass;
    }

    public InboundActionModel setContextClass(Class<?> contextClass) {
        m_contextClass = contextClass;
        return this;
    }

    public java.lang.reflect.Method getErrorMethod() {
        return m_errorMethod;
    }

    public InboundActionModel setErrorMethod(java.lang.reflect.Method errorMethod) {
        m_errorMethod = errorMethod;
        return this;
    }

    public Object getModuleInstance() {
        return m_moduleInstance;
    }

    public InboundActionModel setModuleInstance(Object moduleInstance) {
        m_moduleInstance = moduleInstance;
        return this;
    }

    public Class<?> getPayloadClass() {
        return m_payloadClass;
    }

    public InboundActionModel setPayloadClass(Class<?> payloadClass) {
        m_payloadClass = payloadClass;
        return this;
    }

    public java.lang.reflect.Method getTransitionMethod() {
        return m_transitionMethod;
    }

    public InboundActionModel setTransitionMethod(java.lang.reflect.Method transitionMethod) {
        m_transitionMethod = transitionMethod;
        return this;
    }

    public List<Class<?>> getValidationClasses() {
        return m_validationClasses;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_actionName == null ? 0 : m_actionName.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InboundActionModel) {
            InboundActionModel _o = (InboundActionModel) obj;

            return equals(m_actionName, _o.getActionName());
        }

        return false;
    }

    public String getActionName() {
        return m_actionName;
    }

    public InboundActionModel setActionName(String actionName) {
        m_actionName = actionName;
        return this;
    }

}
