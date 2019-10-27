package org.unidal.dal.jdbc.query;

import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.DataObject;
import org.unidal.dal.jdbc.engine.QueryContext;

import java.util.List;

public interface QueryExecutor {

    <T extends DataObject> List<T> executeQuery(QueryContext ctx) throws DalException;

    int executeUpdate(QueryContext ctx) throws DalException;

    <T extends DataObject> int[] executeUpdateBatch(QueryContext ctx, T[] protos) throws DalException;
}
