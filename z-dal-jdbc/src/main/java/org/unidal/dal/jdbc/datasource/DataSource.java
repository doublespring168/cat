package org.unidal.dal.jdbc.datasource;

import java.sql.Connection;
import java.sql.SQLException;

public interface DataSource {
    Connection getConnection() throws SQLException;

    DataSourceDescriptor getDescriptor();

    void initialize(DataSourceDescriptor descriptor);
}
