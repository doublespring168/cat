package org.unidal.dal.jdbc.engine;

import org.unidal.dal.jdbc.*;
import org.unidal.dal.jdbc.entity.EntityInfo;
import org.unidal.dal.jdbc.query.Parameter;

import java.util.List;
import java.util.Map;

public interface QueryContext {
    void addOutField(DataField field);

    void addOutSubObjectName(String subObjectName);

    void addParameter(Parameter value);

    String getDataSourceName();

    void setDataSourceName(String dataSourceName);

    EntityInfo getEntityInfo();

    void setEntityInfo(EntityInfo entityInfo);

    int getFetchSize();

    void setFetchSize(int fetchSize);

    List<DataField> getOutFields();

    List<String> getOutSubObjectNames();

    List<Parameter> getParameters();

    Object[] getParameterValues();

    void setParameterValues(Object[] values);

    DataObject getProto();

    void setProto(DataObject proto);

    QueryDef getQuery();

    void setQuery(QueryDef query);

    Map<String, Object> getQueryHints();

    void setQueryHints(Map<String, Object> queryHints);

    Readset<?> getReadset();

    void setReadset(Readset<?> readset);

    String getSqlStatement();

    void setSqlStatement(String sqlStatement);

    Updateset<?> getUpdateset();

    void setUpdateset(Updateset<?> updateset);

    boolean isSqlResolveDisabled();

    void setSqlResolveDisabled(boolean sqlResolveDisabled);

    boolean isWithinIfToken();

    void setWithinIfToken(boolean withinIfToken);

    boolean isWithinInToken();

    void setWithinInToken(boolean withinInToken);
}
