package org.unidal.dal.jdbc.mapping;

public interface TableProviderManager {
    TableProvider getTableProvider(String logicalName);
}
