package org.unidal.dal.jdbc.test.data.entity;

import org.unidal.dal.jdbc.test.data.BaseEntity;
import org.unidal.dal.jdbc.test.data.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class DatabaseModel extends BaseEntity<DatabaseModel> {
    private List<TableModel> m_tables = new ArrayList<TableModel>();

    public DatabaseModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitDatabase(this);
    }

    @Override
    public void mergeAttributes(DatabaseModel other) {
    }

    public DatabaseModel addTable(TableModel table) {
        m_tables.add(table);
        return this;
    }

    public TableModel findTable(String name) {
        for (TableModel table : m_tables) {
            if (!equals(table.getName(), name)) {
                continue;
            }

            return table;
        }

        return null;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_tables == null ? 0 : m_tables.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DatabaseModel) {
            DatabaseModel _o = (DatabaseModel) obj;

            return equals(m_tables, _o.getTables());
        }

        return false;
    }

    public List<TableModel> getTables() {
        return m_tables;
    }

    public TableModel removeTable(String name) {
        int len = m_tables.size();

        for (int i = 0; i < len; i++) {
            TableModel table = m_tables.get(i);

            if (!equals(table.getName(), name)) {
                continue;
            }

            return m_tables.remove(i);
        }

        return null;
    }

}
