package org.unidal.dal.jdbc.datasource;

import org.unidal.lookup.ContainerHolder;
import org.unidal.lookup.annotation.Inject;
import org.unidal.lookup.annotation.Named;

import java.util.*;

@Named(type = DataSourceManager.class)
public class DefaultDataSourceManager extends ContainerHolder implements DataSourceManager {
    @Inject
    private JdbcDataSourceDescriptorManager m_manager;

    private Map<String, DataSource> m_dataSources = new HashMap<String, DataSource>();

    @Override
    public List<String> getDataSourceNames() {
        List<String> list = new ArrayList<String>(m_dataSources.keySet());

        Collections.sort(list);
        return list;
    }

    @Override
    public DataSource getDataSource(String name) {
        DataSource dataSource = m_dataSources.get(name);

        if (dataSource == null) {
            synchronized (m_dataSources) {
                dataSource = m_dataSources.get(name);

                if (dataSource == null) {
                    DataSourceDescriptor descriptor = m_manager.getDescriptor(name);

                    if (descriptor == null) {
                        throw new RuntimeException(String.format("No data source(%s) defined!", name));
                    }

                    dataSource = lookup(DataSource.class, descriptor.getType());
                    dataSource.initialize(descriptor);

                    m_dataSources.put(name, dataSource);
                }
            }
        }

        return dataSource;
    }
}
