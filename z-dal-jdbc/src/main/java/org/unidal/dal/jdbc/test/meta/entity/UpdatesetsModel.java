package org.unidal.dal.jdbc.test.meta.entity;

import org.unidal.dal.jdbc.test.meta.BaseEntity;
import org.unidal.dal.jdbc.test.meta.IVisitor;

public class UpdatesetsModel extends BaseEntity<UpdatesetsModel> {
    private UpdatesetModel m_updateset;

    public UpdatesetsModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitUpdatesets(this);
    }

    @Override
    public void mergeAttributes(UpdatesetsModel other) {
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_updateset == null ? 0 : m_updateset.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UpdatesetsModel) {
            UpdatesetsModel _o = (UpdatesetsModel) obj;

            return equals(m_updateset, _o.getUpdateset());
        }

        return false;
    }

    public UpdatesetModel getUpdateset() {
        return m_updateset;
    }

    public UpdatesetsModel setUpdateset(UpdatesetModel updateset) {
        m_updateset = updateset;
        return this;
    }

}
