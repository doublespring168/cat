package org.unidal.dal.jdbc.test.meta.entity;

import org.unidal.dal.jdbc.test.meta.BaseEntity;
import org.unidal.dal.jdbc.test.meta.IVisitor;

public class PrimaryKeyModel extends BaseEntity<PrimaryKeyModel> {
    private String m_name;

    private String m_members;

    public PrimaryKeyModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitPrimaryKey(this);
    }

    @Override
    public void mergeAttributes(PrimaryKeyModel other) {
        if (other.getName() != null) {
            m_name = other.getName();
        }

        if (other.getMembers() != null) {
            m_members = other.getMembers();
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
        hash = hash * 31 + (m_members == null ? 0 : m_members.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PrimaryKeyModel) {
            PrimaryKeyModel _o = (PrimaryKeyModel) obj;

            if (!equals(m_name, _o.getName())) {
                return false;
            }

            return equals(m_members, _o.getMembers());
        }

        return false;
    }

    public String getName() {
        return m_name;
    }

    public String getMembers() {
        return m_members;
    }

    public PrimaryKeyModel setMembers(String members) {
        m_members = members;
        return this;
    }

    public PrimaryKeyModel setName(String name) {
        m_name = name;
        return this;
    }

}
