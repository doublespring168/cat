package org.unidal.dal.jdbc.test.data.transform;

import org.unidal.dal.jdbc.test.data.entity.ColModel;
import org.unidal.dal.jdbc.test.data.entity.DatabaseModel;
import org.unidal.dal.jdbc.test.data.entity.RowModel;
import org.unidal.dal.jdbc.test.data.entity.TableModel;

public interface ILinker {

    boolean onCol(RowModel parent, ColModel col);

    boolean onRow(TableModel parent, RowModel row);

    boolean onTable(DatabaseModel parent, TableModel table);
}
