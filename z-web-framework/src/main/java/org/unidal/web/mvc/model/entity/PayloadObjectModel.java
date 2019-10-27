package org.unidal.web.mvc.model.entity;

import org.unidal.web.mvc.model.BaseEntity;
import org.unidal.web.mvc.model.IVisitor;

public class PayloadObjectModel extends BaseEntity<PayloadObjectModel> {
    private String m_name;

    private java.lang.reflect.Field m_field;

    private java.lang.reflect.Method m_method;

    public PayloadObjectModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitObject(this);
    }

    @Override
    public void mergeAttributes(PayloadObjectModel other) {
        if (other.getName() != null) {
            m_name = other.getName();
        }
    }

    public java.lang.reflect.Field getField() {
        return m_field;
    }

    public PayloadObjectModel setField(java.lang.reflect.Field field) {
        m_field = field;
        return this;
    }

    public java.lang.reflect.Method getMethod() {
        return m_method;
    }

    public PayloadObjectModel setMethod(java.lang.reflect.Method method) {
        m_method = method;
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PayloadObjectModel) {
            PayloadObjectModel _o = (PayloadObjectModel) obj;

            return equals(m_name, _o.getName());
        }

        return false;
    }

    public String getName() {
        return m_name;
    }

    public PayloadObjectModel setName(String name) {
        m_name = name;
        return this;
    }

}
