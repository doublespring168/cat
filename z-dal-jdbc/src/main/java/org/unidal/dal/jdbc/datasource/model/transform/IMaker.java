package org.unidal.dal.jdbc.datasource.model.transform;

import org.unidal.dal.jdbc.datasource.model.entity.DataSourceDef;
import org.unidal.dal.jdbc.datasource.model.entity.DataSourcesDef;
import org.unidal.dal.jdbc.datasource.model.entity.PropertiesDef;

public interface IMaker<T> {

    DataSourceDef buildDataSource(T node);

    DataSourcesDef buildDataSources(T node);

    PropertiesDef buildProperties(T node);
}
