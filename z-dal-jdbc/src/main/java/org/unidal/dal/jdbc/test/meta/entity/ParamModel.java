package org.unidal.dal.jdbc.test.meta.entity;

import org.unidal.dal.jdbc.test.meta.BaseEntity;
import org.unidal.dal.jdbc.test.meta.IVisitor;

public class ParamModel extends BaseEntity<ParamModel> {
    private String m_name;

    public ParamModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitParam(this);
    }

    @Override
    public void mergeAttributes(ParamModel other) {
        if (other.getName() != null) {
            m_name = other.getName();
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ParamModel) {
            ParamModel _o = (ParamModel) obj;

            return equals(m_name, _o.getName());
        }

        return false;
    }

    public String getName() {
        return m_name;
    }

    public ParamModel setName(String name) {
        m_name = name;
        return this;
    }

}
