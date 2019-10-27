package org.unidal.dal.jdbc.datasource.model.transform;

import org.unidal.dal.jdbc.datasource.model.entity.DataSourceDef;
import org.unidal.dal.jdbc.datasource.model.entity.DataSourcesDef;
import org.unidal.dal.jdbc.datasource.model.entity.PropertiesDef;

public interface ILinker {

    boolean onDataSource(DataSourcesDef parent, DataSourceDef dataSource);

    boolean onProperties(DataSourceDef parent, PropertiesDef properties);
}
