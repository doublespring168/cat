package org.unidal.dal.jdbc.test.data.entity;

import org.unidal.dal.jdbc.test.data.BaseEntity;
import org.unidal.dal.jdbc.test.data.IVisitor;

import static org.unidal.dal.jdbc.test.data.Constants.ATTR_NAME;
import static org.unidal.dal.jdbc.test.data.Constants.ENTITY_COL;

public class ColModel extends BaseEntity<ColModel> {
    private String m_name;

    private Boolean m_key;

    private String m_text;

    public ColModel() {
    }

    public ColModel(String name) {
        m_name = name;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitCol(this);
    }

    @Override
    public void mergeAttributes(ColModel other) {
        assertAttributeEquals(other, ENTITY_COL, ATTR_NAME, m_name, other.getName());

        if (other.getKey() != null) {
            m_key = other.getKey();
        }
    }

    public Boolean getKey() {
        return m_key;
    }

    public String getText() {
        return m_text;
    }

    public ColModel setText(String text) {
        m_text = text;
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
        if (obj instanceof ColModel) {
            ColModel _o = (ColModel) obj;

            return equals(m_name, _o.getName());
        }

        return false;
    }

    public String getName() {
        return m_name;
    }

    public ColModel setName(String name) {
        m_name = name;
        return this;
    }

    public boolean isKey() {
        return m_key != null && m_key.booleanValue();
    }

    public ColModel setKey(Boolean key) {
        m_key = key;
        return this;
    }

}
