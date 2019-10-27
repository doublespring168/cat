package org.unidal.dal.jdbc.datasource;

import org.unidal.dal.jdbc.datasource.model.entity.DataSourcesDef;

public interface DataSourceProvider {
    DataSourcesDef defineDatasources();
}
