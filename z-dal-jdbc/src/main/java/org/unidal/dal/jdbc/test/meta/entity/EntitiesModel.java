package org.unidal.dal.jdbc.test.meta.entity;

import org.unidal.dal.jdbc.test.meta.BaseEntity;
import org.unidal.dal.jdbc.test.meta.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class EntitiesModel extends BaseEntity<EntitiesModel> {
    private List<EntityModel> m_entities = new ArrayList<EntityModel>();

    public EntitiesModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitEntities(this);
    }

    @Override
    public void mergeAttributes(EntitiesModel other) {
    }

    public EntitiesModel addEntity(EntityModel entity) {
        m_entities.add(entity);
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_entities == null ? 0 : m_entities.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EntitiesModel) {
            EntitiesModel _o = (EntitiesModel) obj;

            return equals(m_entities, _o.getEntities());
        }

        return false;
    }

    public List<EntityModel> getEntities() {
        return m_entities;
    }

}
