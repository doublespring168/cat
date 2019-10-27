package org.unidal.dal.jdbc.query;

import org.unidal.dal.jdbc.DalException;
import org.unidal.dal.jdbc.DataObject;
import org.unidal.dal.jdbc.engine.QueryContext;

public interface WriteHandler {
    int executeUpdate(QueryContext ctx) throws DalException;

    <T extends DataObject> int[] executeUpdateBatch(QueryContext ctx, T[] protos) throws DalException;
}
