package org.unidal.dal.jdbc.datasource;

import java.util.List;

public interface DataSourceManager {
    List<String> getDataSourceNames();

    DataSource getDataSource(String dataSourceName);
}
