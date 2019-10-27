package org.unidal.dal.jdbc.test.data.entity;

import org.unidal.dal.jdbc.test.data.BaseEntity;
import org.unidal.dal.jdbc.test.data.IVisitor;

import java.util.ArrayList;
import java.util.List;

import static org.unidal.dal.jdbc.test.data.Constants.ATTR_ID;
import static org.unidal.dal.jdbc.test.data.Constants.ENTITY_ROW;

public class RowModel extends BaseEntity<RowModel> {
    private String m_status;

    private List<ColModel> m_cols = new ArrayList<ColModel>();

    private String m_id;

    public RowModel() {
    }

    public RowModel(String id) {
        m_id = id;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitRow(this);
    }

    @Override
    public void mergeAttributes(RowModel other) {
        assertAttributeEquals(other, ENTITY_ROW, ATTR_ID, m_id, other.getId());

        if (other.getStatus() != null) {
            m_status = other.getStatus();
        }
    }

    public String getStatus() {
        return m_status;
    }

    public RowModel setStatus(String status) {
        m_status = status;
        return this;
    }

    public RowModel addCol(ColModel col) {
        m_cols.add(col);
        return this;
    }

    public ColModel findCol(String name) {
        for (ColModel col : m_cols) {
            if (!equals(col.getName(), name)) {
                continue;
            }

            return col;
        }

        return null;
    }

    public List<ColModel> getCols() {
        return m_cols;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_id == null ? 0 : m_id.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RowModel) {
            RowModel _o = (RowModel) obj;

            return equals(m_id, _o.getId());
        }

        return false;
    }

    public String getId() {
        return m_id;
    }

    public RowModel setId(String id) {
        m_id = id;
        return this;
    }

    public ColModel removeCol(String name) {
        int len = m_cols.size();

        for (int i = 0; i < len; i++) {
            ColModel col = m_cols.get(i);

            if (!equals(col.getName(), name)) {
                continue;
            }

            return m_cols.remove(i);
        }

        return null;
    }

}
