package org.unidal.dal.jdbc.query;

import org.unidal.dal.jdbc.engine.QueryContext;

public interface QueryResolver {
    void resolve(QueryContext ctx);
}
