package org.unidal.dal.jdbc.test.meta.entity;

import org.unidal.dal.jdbc.test.meta.BaseEntity;
import org.unidal.dal.jdbc.test.meta.IVisitor;

public class UpdatesetModel extends BaseEntity<UpdatesetModel> {
    private String m_name;

    private Boolean m_all;

    public UpdatesetModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitUpdateset(this);
    }

    @Override
    public void mergeAttributes(UpdatesetModel other) {
        if (other.getName() != null) {
            m_name = other.getName();
        }

        if (other.getAll() != null) {
            m_all = other.getAll();
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
        hash = hash * 31 + (m_all == null ? 0 : m_all.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UpdatesetModel) {
            UpdatesetModel _o = (UpdatesetModel) obj;

            if (!equals(m_name, _o.getName())) {
                return false;
            }

            return equals(m_all, _o.getAll());
        }

        return false;
    }

    public String getName() {
        return m_name;
    }

    public Boolean getAll() {
        return m_all;
    }

    public UpdatesetModel setName(String name) {
        m_name = name;
        return this;
    }

    public boolean isAll() {
        return m_all != null && m_all.booleanValue();
    }

    public UpdatesetModel setAll(Boolean all) {
        m_all = all;
        return this;
    }

}
