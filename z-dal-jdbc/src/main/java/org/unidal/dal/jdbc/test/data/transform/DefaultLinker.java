package org.unidal.dal.jdbc.test.data.transform;

import org.unidal.dal.jdbc.test.data.entity.ColModel;
import org.unidal.dal.jdbc.test.data.entity.DatabaseModel;
import org.unidal.dal.jdbc.test.data.entity.RowModel;
import org.unidal.dal.jdbc.test.data.entity.TableModel;

import java.util.ArrayList;
import java.util.List;

public class DefaultLinker implements ILinker {
    @SuppressWarnings("unused")
    private boolean m_deferrable;

    private List<Runnable> m_deferedJobs = new ArrayList<Runnable>();

    public DefaultLinker(boolean deferrable) {
        m_deferrable = deferrable;
    }

    public void finish() {
        for (Runnable job : m_deferedJobs) {
            job.run();
        }
    }

    @Override
    public boolean onCol(final RowModel parent, final ColModel col) {
        parent.addCol(col);
        return true;
    }

    @Override
    public boolean onRow(final TableModel parent, final RowModel row) {
        parent.addRow(row);
        return true;
    }

    @Override
    public boolean onTable(final DatabaseModel parent, final TableModel table) {
        parent.addTable(table);
        return true;
    }
}
