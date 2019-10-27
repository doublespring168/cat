package org.unidal.dal.jdbc.configuration;

import org.unidal.dal.jdbc.QueryEngine;
import org.unidal.dal.jdbc.annotation.Entity;
import org.unidal.dal.jdbc.datasource.DataSourceProvider;
import org.unidal.dal.jdbc.datasource.DefaultDataSourceProvider;
import org.unidal.dal.jdbc.mapping.SimpleTableProvider;
import org.unidal.dal.jdbc.mapping.TableProvider;
import org.unidal.lookup.configuration.AbstractResourceConfigurator;
import org.unidal.lookup.configuration.Component;

import java.util.List;

public abstract class AbstractJdbcResourceConfigurator extends AbstractResourceConfigurator {
    protected void defineDaoComponents(List<Component> all, Class<?>[] daoClasses) {
        for (Class<?> daoClass : daoClasses) {
            all.add(C(daoClass).req(QueryEngine.class));
        }
    }

    protected Component defineJdbcDataSourceConfigurationManagerComponent(String datasourceFile) {
        return C(DataSourceProvider.class, DefaultDataSourceProvider.class) //
                .config(E("datasourceFile").value(datasourceFile));
    }

    protected Component defineSimpleTableProviderComponent(String dataSource, String logicalTableName) {
        String physicalTableName = logicalTableName.replace('-', '_');

        return defineSimpleTableProviderComponent(dataSource, logicalTableName, physicalTableName);
    }

    protected Component defineSimpleTableProviderComponent(String dataSource, String logicalTableName,
                                                           String physicalTableName) {
        return C(TableProvider.class, logicalTableName, SimpleTableProvider.class).config(
                E("physical-table-name").value(physicalTableName), E("data-source-name").value(dataSource));
    }

    protected void defineSimpleTableProviderComponents(List<Component> all, String dataSource, Class<?>[] entityClasses) {
        for (Class<?> entityClass : entityClasses) {
            Entity entity = entityClass.getAnnotation(Entity.class);
            String logicalName = entity.logicalName();
            String physicalName = entity.physicalName();

            if (physicalName.length() == 0) {
                logicalName.replace('-', '_');
            }

            all.add(defineSimpleTableProviderComponent(dataSource, logicalName, physicalName));
        }
    }
}
