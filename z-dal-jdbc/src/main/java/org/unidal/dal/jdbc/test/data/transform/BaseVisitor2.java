package org.unidal.dal.jdbc.test.data.transform;

import org.unidal.dal.jdbc.test.data.IVisitor;
import org.unidal.dal.jdbc.test.data.entity.ColModel;
import org.unidal.dal.jdbc.test.data.entity.DatabaseModel;
import org.unidal.dal.jdbc.test.data.entity.RowModel;
import org.unidal.dal.jdbc.test.data.entity.TableModel;

import java.util.Stack;

public abstract class BaseVisitor2 implements IVisitor {

    private Stack<Object> m_parents = new Stack<Object>();

    @SuppressWarnings("unchecked")
    protected final <T> T getParent() {
        return (T) getAncestor(1);
    }

    /**
     * Get parent object if have.
     *
     * @param backLevels 1 means parent, 2 means parent of parent, and so on.
     * @return parent object, null if not exists.
     */
    @SuppressWarnings("unchecked")
    protected final <T> T getAncestor(int backLevels) {
        if (m_parents.isEmpty()) {
            return null;
        } else if (backLevels == 1) {
            return (T) m_parents.peek();
        } else {
            int size = m_parents.size();

            if (backLevels <= size) {
                return (T) m_parents.get(size - backLevels);
            } else {
                return null;
            }
        }
    }

    protected final Stack<Object> getStack() {
        return m_parents;
    }

    @Override
    public final void visitCol(ColModel col) {
        m_parents.push(col);
        visitColChildren(col);
        m_parents.pop();
    }

    protected void visitColChildren(ColModel col) {
    }

    @Override
    public final void visitDatabase(DatabaseModel database) {
        m_parents.push(database);
        visitDatabaseChildren(database);
        m_parents.pop();
    }

    protected void visitDatabaseChildren(DatabaseModel database) {
        for (TableModel table : database.getTables()) {
            visitTable(table);
        }
    }

    @Override
    public final void visitRow(RowModel row) {
        m_parents.push(row);
        visitRowChildren(row);
        m_parents.pop();
    }

    protected void visitRowChildren(RowModel row) {
        for (ColModel col : row.getCols()) {
            visitCol(col);
        }
    }

    @Override
    public final void visitTable(TableModel table) {
        m_parents.push(table);
        visitTableChildren(table);
        m_parents.pop();
    }

    protected void visitTableChildren(TableModel table) {
        for (RowModel row : table.getRows()) {
            visitRow(row);
        }
    }
}
