package org.unidal.dal.jdbc.entity;

import org.unidal.dal.jdbc.DataObject;
import org.unidal.dal.jdbc.engine.QueryContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DataObjectAssembly {
    <T extends DataObject> List<T> assemble(QueryContext ctx, ResultSet rs) throws SQLException;
}
