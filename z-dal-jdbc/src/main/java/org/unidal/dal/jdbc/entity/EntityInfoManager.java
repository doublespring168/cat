package org.unidal.dal.jdbc.entity;

public interface EntityInfoManager {
    EntityInfo getEntityInfo(Class<?> entityClass);

    EntityInfo getEntityInfo(String logicalName);

    void register(Class<?> entityClass);

}