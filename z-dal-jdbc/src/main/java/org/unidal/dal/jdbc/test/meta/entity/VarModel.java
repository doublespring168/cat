package org.unidal.dal.jdbc.test.meta.entity;

import org.unidal.dal.jdbc.test.meta.BaseEntity;
import org.unidal.dal.jdbc.test.meta.IVisitor;

public class VarModel extends BaseEntity<VarModel> {
    private String m_name;

    private String m_valueType;

    private String m_keyMember;

    public VarModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitVar(this);
    }

    @Override
    public void mergeAttributes(VarModel other) {
        if (other.getName() != null) {
            m_name = other.getName();
        }

        if (other.getValueType() != null) {
            m_valueType = other.getValueType();
        }

        if (other.getKeyMember() != null) {
            m_keyMember = other.getKeyMember();
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
        hash = hash * 31 + (m_valueType == null ? 0 : m_valueType.hashCode());
        hash = hash * 31 + (m_keyMember == null ? 0 : m_keyMember.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VarModel) {
            VarModel _o = (VarModel) obj;

            if (!equals(m_name, _o.getName())) {
                return false;
            }

            if (!equals(m_valueType, _o.getValueType())) {
                return false;
            }

            return equals(m_keyMember, _o.getKeyMember());
        }

        return false;
    }

    public String getName() {
        return m_name;
    }

    public String getValueType() {
        return m_valueType;
    }

    public String getKeyMember() {
        return m_keyMember;
    }

    public VarModel setKeyMember(String keyMember) {
        m_keyMember = keyMember;
        return this;
    }

    public VarModel setValueType(String valueType) {
        m_valueType = valueType;
        return this;
    }

    public VarModel setName(String name) {
        m_name = name;
        return this;
    }

}
