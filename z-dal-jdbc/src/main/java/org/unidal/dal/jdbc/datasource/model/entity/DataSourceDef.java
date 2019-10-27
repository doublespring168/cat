package org.unidal.dal.jdbc.datasource.model.entity;

import org.unidal.dal.jdbc.datasource.model.BaseEntity;
import org.unidal.dal.jdbc.datasource.model.IVisitor;

import static org.unidal.dal.jdbc.datasource.model.Constants.ATTR_ID;
import static org.unidal.dal.jdbc.datasource.model.Constants.ENTITY_DATA_SOURCE;

public class DataSourceDef extends BaseEntity<DataSourceDef> {
    private String m_id;

    private String m_type = "jdbc";

    private Integer m_minimumPoolSize = 1;

    private Integer m_maximumPoolSize = 3;

    private String m_connectionTimeout = "1s";

    private String m_idleTimeout = "30m";

    private Integer m_statementCacheSize = 1000;

    private PropertiesDef m_properties;

    public DataSourceDef() {
    }

    public DataSourceDef(String id) {
        m_id = id;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitDataSource(this);
    }

    @Override
    public void mergeAttributes(DataSourceDef other) {
        assertAttributeEquals(other, ENTITY_DATA_SOURCE, ATTR_ID, m_id, other.getId());

        if (other.getType() != null) {
            m_type = other.getType();
        }
    }

    public String getType() {
        return m_type;
    }

    public DataSourceDef setType(String type) {
        m_type = type;
        return this;
    }

    public String getConnectionTimeout() {
        return m_connectionTimeout;
    }

    public DataSourceDef setConnectionTimeout(String connectionTimeout) {
        m_connectionTimeout = connectionTimeout;
        return this;
    }

    public String getIdleTimeout() {
        return m_idleTimeout;
    }

    public DataSourceDef setIdleTimeout(String idleTimeout) {
        m_idleTimeout = idleTimeout;
        return this;
    }

    public Integer getMaximumPoolSize() {
        return m_maximumPoolSize;
    }

    public DataSourceDef setMaximumPoolSize(Integer maximumPoolSize) {
        m_maximumPoolSize = maximumPoolSize;
        return this;
    }

    public Integer getMinimumPoolSize() {
        return m_minimumPoolSize;
    }

    public DataSourceDef setMinimumPoolSize(Integer minimumPoolSize) {
        m_minimumPoolSize = minimumPoolSize;
        return this;
    }

    public PropertiesDef getProperties() {
        return m_properties;
    }

    public DataSourceDef setProperties(PropertiesDef properties) {
        m_properties = properties;
        return this;
    }

    public Integer getStatementCacheSize() {
        return m_statementCacheSize;
    }

    public DataSourceDef setStatementCacheSize(Integer statementCacheSize) {
        m_statementCacheSize = statementCacheSize;
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DataSourceDef) {
            DataSourceDef _o = (DataSourceDef) obj;

            return equals(m_id, _o.getId());
        }

        return false;
    }

    public String getId() {
        return m_id;
    }

    public DataSourceDef setId(String id) {
        m_id = id;
        return this;
    }

}
