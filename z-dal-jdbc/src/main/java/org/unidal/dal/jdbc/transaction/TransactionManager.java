package org.unidal.dal.jdbc.transaction;

import org.unidal.dal.jdbc.engine.QueryContext;

import java.sql.Connection;

public interface TransactionManager {
    void closeConnection();

    void commitTransaction();

    Connection getConnection(QueryContext ctx);

    boolean isInTransaction();

    void rollbackTransaction();

    void startTransaction(String datasource);
}
