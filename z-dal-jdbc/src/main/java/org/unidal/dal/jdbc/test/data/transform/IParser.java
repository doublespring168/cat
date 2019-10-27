package org.unidal.dal.jdbc.test.data.transform;

import org.unidal.dal.jdbc.test.data.entity.ColModel;
import org.unidal.dal.jdbc.test.data.entity.DatabaseModel;
import org.unidal.dal.jdbc.test.data.entity.RowModel;
import org.unidal.dal.jdbc.test.data.entity.TableModel;

public interface IParser<T> {
    DatabaseModel parse(IMaker<T> maker, ILinker linker, T node);

    void parseForColModel(IMaker<T> maker, ILinker linker, ColModel parent, T node);

    void parseForRowModel(IMaker<T> maker, ILinker linker, RowModel parent, T node);

    void parseForTableModel(IMaker<T> maker, ILinker linker, TableModel parent, T node);
}
