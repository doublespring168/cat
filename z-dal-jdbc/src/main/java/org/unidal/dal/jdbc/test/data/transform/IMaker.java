package org.unidal.dal.jdbc.test.data.transform;

import org.unidal.dal.jdbc.test.data.entity.ColModel;
import org.unidal.dal.jdbc.test.data.entity.DatabaseModel;
import org.unidal.dal.jdbc.test.data.entity.RowModel;
import org.unidal.dal.jdbc.test.data.entity.TableModel;

public interface IMaker<T> {

    ColModel buildCol(T node);

    DatabaseModel buildDatabase(T node);

    RowModel buildRow(T node);

    TableModel buildTable(T node);
}
