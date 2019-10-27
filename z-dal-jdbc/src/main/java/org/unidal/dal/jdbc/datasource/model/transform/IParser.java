package org.unidal.dal.jdbc.datasource.model.transform;

import org.unidal.dal.jdbc.datasource.model.entity.DataSourceDef;
import org.unidal.dal.jdbc.datasource.model.entity.DataSourcesDef;
import org.unidal.dal.jdbc.datasource.model.entity.PropertiesDef;

public interface IParser<T> {
    DataSourcesDef parse(IMaker<T> maker, ILinker linker, T node);

    void parseForDataSourceDef(IMaker<T> maker, ILinker linker, DataSourceDef parent, T node);

    void parseForPropertiesDef(IMaker<T> maker, ILinker linker, PropertiesDef parent, T node);
}
