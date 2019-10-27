package org.unidal.dal.jdbc.test.meta.entity;

import org.unidal.dal.jdbc.test.meta.BaseEntity;
import org.unidal.dal.jdbc.test.meta.IVisitor;

public class IndexModel extends BaseEntity<IndexModel> {
    private String m_name;

    private String m_members;

    private Boolean m_unique;

    public IndexModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitIndex(this);
    }

    @Override
    public void mergeAttributes(IndexModel other) {
        if (other.getName() != null) {
            m_name = other.getName();
        }

        if (other.getMembers() != null) {
            m_members = other.getMembers();
        }

        if (other.getUnique() != null) {
            m_unique = other.getUnique();
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
        hash = hash * 31 + (m_members == null ? 0 : m_members.hashCode());
        hash = hash * 31 + (m_unique == null ? 0 : m_unique.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof IndexModel) {
            IndexModel _o = (IndexModel) obj;

            if (!equals(m_name, _o.getName())) {
                return false;
            }

            if (!equals(m_members, _o.getMembers())) {
                return false;
            }

            return equals(m_unique, _o.getUnique());
        }

        return false;
    }

    public String getName() {
        return m_name;
    }

    public String getMembers() {
        return m_members;
    }

    public Boolean getUnique() {
        return m_unique;
    }

    public IndexModel setMembers(String members) {
        m_members = members;
        return this;
    }

    public IndexModel setName(String name) {
        m_name = name;
        return this;
    }

    public boolean isUnique() {
        return m_unique != null && m_unique.booleanValue();
    }

    public IndexModel setUnique(Boolean unique) {
        m_unique = unique;
        return this;
    }

}
