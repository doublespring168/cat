package org.unidal.dal.jdbc.test.meta.entity;

import org.unidal.dal.jdbc.test.meta.BaseEntity;
import org.unidal.dal.jdbc.test.meta.IVisitor;

public class ReadsetsModel extends BaseEntity<ReadsetsModel> {
    private ReadsetModel m_readset;

    public ReadsetsModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitReadsets(this);
    }

    @Override
    public void mergeAttributes(ReadsetsModel other) {
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_readset == null ? 0 : m_readset.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ReadsetsModel) {
            ReadsetsModel _o = (ReadsetsModel) obj;

            return equals(m_readset, _o.getReadset());
        }

        return false;
    }

    public ReadsetModel getReadset() {
        return m_readset;
    }

    public ReadsetsModel setReadset(ReadsetModel readset) {
        m_readset = readset;
        return this;
    }

}
