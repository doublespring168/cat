package org.unidal.dal.jdbc.test.meta.entity;

import org.unidal.dal.jdbc.test.meta.BaseEntity;
import org.unidal.dal.jdbc.test.meta.IVisitor;

public class MemberModel extends BaseEntity<MemberModel> {
    private String m_name;

    private String m_field;

    private String m_valueType;

    private Integer m_length;

    private Boolean m_nullable;

    private Boolean m_key;

    private Boolean m_autoIncrement;

    private String m_insertExpr;

    private String m_updateExpr;

    public MemberModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitMember(this);
    }

    @Override
    public void mergeAttributes(MemberModel other) {
        if (other.getName() != null) {
            m_name = other.getName();
        }

        if (other.getField() != null) {
            m_field = other.getField();
        }

        if (other.getValueType() != null) {
            m_valueType = other.getValueType();
        }

        if (other.getLength() != null) {
            m_length = other.getLength();
        }

        if (other.getNullable() != null) {
            m_nullable = other.getNullable();
        }

        if (other.getKey() != null) {
            m_key = other.getKey();
        }

        if (other.getAutoIncrement() != null) {
            m_autoIncrement = other.getAutoIncrement();
        }

        if (other.getInsertExpr() != null) {
            m_insertExpr = other.getInsertExpr();
        }

        if (other.getUpdateExpr() != null) {
            m_updateExpr = other.getUpdateExpr();
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
        hash = hash * 31 + (m_field == null ? 0 : m_field.hashCode());
        hash = hash * 31 + (m_valueType == null ? 0 : m_valueType.hashCode());
        hash = hash * 31 + (m_length == null ? 0 : m_length.hashCode());
        hash = hash * 31 + (m_nullable == null ? 0 : m_nullable.hashCode());
        hash = hash * 31 + (m_key == null ? 0 : m_key.hashCode());
        hash = hash * 31 + (m_autoIncrement == null ? 0 : m_autoIncrement.hashCode());
        hash = hash * 31 + (m_insertExpr == null ? 0 : m_insertExpr.hashCode());
        hash = hash * 31 + (m_updateExpr == null ? 0 : m_updateExpr.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MemberModel) {
            MemberModel _o = (MemberModel) obj;

            if (!equals(m_name, _o.getName())) {
                return false;
            }

            if (!equals(m_field, _o.getField())) {
                return false;
            }

            if (!equals(m_valueType, _o.getValueType())) {
                return false;
            }

            if (!equals(m_length, _o.getLength())) {
                return false;
            }

            if (!equals(m_nullable, _o.getNullable())) {
                return false;
            }

            if (!equals(m_key, _o.getKey())) {
                return false;
            }

            if (!equals(m_autoIncrement, _o.getAutoIncrement())) {
                return false;
            }

            if (!equals(m_insertExpr, _o.getInsertExpr())) {
                return false;
            }

            return equals(m_updateExpr, _o.getUpdateExpr());
        }

        return false;
    }

    public String getName() {
        return m_name;
    }

    public String getField() {
        return m_field;
    }

    public String getValueType() {
        return m_valueType;
    }

    public Integer getLength() {
        return m_length;
    }

    public Boolean getNullable() {
        return m_nullable;
    }

    public Boolean getKey() {
        return m_key;
    }

    public Boolean getAutoIncrement() {
        return m_autoIncrement;
    }

    public String getInsertExpr() {
        return m_insertExpr;
    }

    public String getUpdateExpr() {
        return m_updateExpr;
    }

    public MemberModel setUpdateExpr(String updateExpr) {
        m_updateExpr = updateExpr;
        return this;
    }

    public MemberModel setInsertExpr(String insertExpr) {
        m_insertExpr = insertExpr;
        return this;
    }

    public MemberModel setLength(Integer length) {
        m_length = length;
        return this;
    }

    public MemberModel setValueType(String valueType) {
        m_valueType = valueType;
        return this;
    }

    public MemberModel setField(String field) {
        m_field = field;
        return this;
    }

    public MemberModel setName(String name) {
        m_name = name;
        return this;
    }

    public boolean isAutoIncrement() {
        return m_autoIncrement != null && m_autoIncrement.booleanValue();
    }

    public MemberModel setAutoIncrement(Boolean autoIncrement) {
        m_autoIncrement = autoIncrement;
        return this;
    }

    public boolean isKey() {
        return m_key != null && m_key.booleanValue();
    }

    public MemberModel setKey(Boolean key) {
        m_key = key;
        return this;
    }

    public boolean isNullable() {
        return m_nullable != null && m_nullable.booleanValue();
    }

    public MemberModel setNullable(Boolean nullable) {
        m_nullable = nullable;
        return this;
    }

}
