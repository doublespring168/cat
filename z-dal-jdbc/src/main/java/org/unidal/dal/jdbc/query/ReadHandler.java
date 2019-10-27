package org.unidal.dal.jdbc.query;

import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.DataObject;
import org.unidal.dal.jdbc.engine.QueryContext;

import java.util.List;

public interface ReadHandler {
    <T extends DataObject> List<T> executeQuery(QueryContext ctx) throws DalException;
}
