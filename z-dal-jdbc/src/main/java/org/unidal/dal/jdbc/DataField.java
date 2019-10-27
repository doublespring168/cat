package org.unidal.dal.jdbc;

public class DataField {
    private Class<?> m_entityClass;

    private int m_index;

    private String m_name;

    public DataField(String name) {
        m_name = name;
    }

    public Class<?> getEntityClass() {
        return m_entityClass;
    }

    public void setEntityClass(Class<?> entityClass) {
        m_entityClass = entityClass;
    }

    public int getIndex() {
        return m_index;
    }

    public void setIndex(int index) {
        m_index = index;
    }

    public String getName() {
        return m_name;
    }

    @Override
    public String toString() {
        return m_name;
    }
}
