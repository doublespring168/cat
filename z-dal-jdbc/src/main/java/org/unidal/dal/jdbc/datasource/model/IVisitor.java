package org.unidal.dal.jdbc.datasource.model;

import org.unidal.dal.jdbc.datasource.model.entity.DataSourceDef;
import org.unidal.dal.jdbc.datasource.model.entity.DataSourcesDef;
import org.unidal.dal.jdbc.datasource.model.entity.PropertiesDef;

public interface IVisitor {

    void visitDataSource(DataSourceDef dataSource);

    void visitDataSources(DataSourcesDef dataSources);

    void visitProperties(PropertiesDef properties);
}
