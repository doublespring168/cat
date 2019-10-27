package org.unidal.dal.jdbc.test.data.entity;

import org.unidal.dal.jdbc.test.data.BaseEntity;
import org.unidal.dal.jdbc.test.data.IVisitor;

import java.util.ArrayList;
import java.util.List;

import static org.unidal.dal.jdbc.test.data.Constants.ATTR_NAME;
import static org.unidal.dal.jdbc.test.data.Constants.ENTITY_TABLE;

public class TableModel extends BaseEntity<TableModel> {
    private String m_name;

    private List<RowModel> m_rows = new ArrayList<RowModel>();

    public TableModel() {
    }

    public TableModel(String name) {
        m_name = name;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitTable(this);
    }

    @Override
    public void mergeAttributes(TableModel other) {
        assertAttributeEquals(other, ENTITY_TABLE, ATTR_NAME, m_name, other.getName());

    }

    public TableModel addRow(RowModel row) {
        m_rows.add(row);
        return this;
    }

    public RowModel findRow(String id) {
        for (RowModel row : m_rows) {
            if (!equals(row.getId(), id)) {
                continue;
            }

            return row;
        }

        return null;
    }

    public RowModel findOrCreateRow(String id) {
        synchronized (m_rows) {
            for (RowModel row : m_rows) {
                if (!equals(row.getId(), id)) {
                    continue;
                }

                return row;
            }

            RowModel row = new RowModel(id);

            m_rows.add(row);
            return row;
        }
    }

    public List<RowModel> getRows() {
        return m_rows;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TableModel) {
            TableModel _o = (TableModel) obj;

            return equals(m_name, _o.getName());
        }

        return false;
    }

    public String getName() {
        return m_name;
    }

    public TableModel setName(String name) {
        m_name = name;
        return this;
    }

    public RowModel removeRow(String id) {
        int len = m_rows.size();

        for (int i = 0; i < len; i++) {
            RowModel row = m_rows.get(i);

            if (!equals(row.getId(), id)) {
                continue;
            }

            return m_rows.remove(i);
        }

        return null;
    }

}
