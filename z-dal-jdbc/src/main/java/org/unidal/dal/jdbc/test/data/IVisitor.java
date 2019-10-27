package org.unidal.dal.jdbc.test.data;

import org.unidal.dal.jdbc.test.data.entity.ColModel;
import org.unidal.dal.jdbc.test.data.entity.DatabaseModel;
import org.unidal.dal.jdbc.test.data.entity.RowModel;
import org.unidal.dal.jdbc.test.data.entity.TableModel;

public interface IVisitor {

    void visitCol(ColModel col);

    void visitDatabase(DatabaseModel database);

    void visitRow(RowModel row);

    void visitTable(TableModel table);
}
