package org.unidal.dal.jdbc.datasource.model.entity;

import org.unidal.dal.jdbc.datasource.model.BaseEntity;
import org.unidal.dal.jdbc.datasource.model.IVisitor;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataSourcesDef extends BaseEntity<DataSourcesDef> {
    private Map<String, DataSourceDef> m_dataSourcesMap = new LinkedHashMap<String, DataSourceDef>();

    public DataSourcesDef() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitDataSources(this);
    }

    @Override
    public void mergeAttributes(DataSourcesDef other) {
    }

    public DataSourcesDef addDataSource(DataSourceDef dataSource) {
        m_dataSourcesMap.put(dataSource.getId(), dataSource);
        return this;
    }

    public DataSourceDef findDataSource(String id) {
        return m_dataSourcesMap.get(id);
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_dataSourcesMap == null ? 0 : m_dataSourcesMap.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DataSourcesDef) {
            DataSourcesDef _o = (DataSourcesDef) obj;

            return equals(m_dataSourcesMap, _o.getDataSourcesMap());
        }

        return false;
    }

    public Map<String, DataSourceDef> getDataSourcesMap() {
        return m_dataSourcesMap;
    }

    public DataSourceDef removeDataSource(String id) {
        return m_dataSourcesMap.remove(id);
    }

}
