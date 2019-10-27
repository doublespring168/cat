package org.unidal.dal.jdbc.query;

public interface QueryNaming {
    String getTable(String table);

    String getField(String field);

    String getField(String field, String tableAlias);

    String getTable(String table, String alias);
}
