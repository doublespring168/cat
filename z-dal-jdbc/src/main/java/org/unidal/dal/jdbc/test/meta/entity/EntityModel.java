package org.unidal.dal.jdbc.test.meta.entity;

import org.unidal.dal.jdbc.test.meta.BaseEntity;
import org.unidal.dal.jdbc.test.meta.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class EntityModel extends BaseEntity<EntityModel> {
    private String m_name;

    private String m_table;

    private String m_alias;

    private List<MemberModel> m_members = new ArrayList<MemberModel>();

    private VarModel m_var;

    private PrimaryKeyModel m_primaryKey;

    private ReadsetsModel m_readsets;

    private UpdatesetsModel m_updatesets;

    private List<QueryModel> m_queryDefs = new ArrayList<QueryModel>();

    private List<IndexModel> m_indexs = new ArrayList<IndexModel>();

    public EntityModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitEntity(this);
    }

    @Override
    public void mergeAttributes(EntityModel other) {
        if (other.getName() != null) {
            m_name = other.getName();
        }

        if (other.getTable() != null) {
            m_table = other.getTable();
        }

        if (other.getAlias() != null) {
            m_alias = other.getAlias();
        }
    }

    public EntityModel addIndex(IndexModel index) {
        m_indexs.add(index);
        return this;
    }

    public EntityModel addMember(MemberModel member) {
        m_members.add(member);
        return this;
    }

    public EntityModel addQuery(QueryModel query) {
        m_queryDefs.add(query);
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
        hash = hash * 31 + (m_table == null ? 0 : m_table.hashCode());
        hash = hash * 31 + (m_alias == null ? 0 : m_alias.hashCode());
        hash = hash * 31 + (m_members == null ? 0 : m_members.hashCode());
        hash = hash * 31 + (m_var == null ? 0 : m_var.hashCode());
        hash = hash * 31 + (m_primaryKey == null ? 0 : m_primaryKey.hashCode());
        hash = hash * 31 + (m_readsets == null ? 0 : m_readsets.hashCode());
        hash = hash * 31 + (m_updatesets == null ? 0 : m_updatesets.hashCode());
        hash = hash * 31 + (m_queryDefs == null ? 0 : m_queryDefs.hashCode());
        hash = hash * 31 + (m_indexs == null ? 0 : m_indexs.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof EntityModel) {
            EntityModel _o = (EntityModel) obj;

            if (!equals(m_name, _o.getName())) {
                return false;
            }

            if (!equals(m_table, _o.getTable())) {
                return false;
            }

            if (!equals(m_alias, _o.getAlias())) {
                return false;
            }

            if (!equals(m_members, _o.getMembers())) {
                return false;
            }

            if (!equals(m_var, _o.getVar())) {
                return false;
            }

            if (!equals(m_primaryKey, _o.getPrimaryKey())) {
                return false;
            }

            if (!equals(m_readsets, _o.getReadsets())) {
                return false;
            }

            if (!equals(m_updatesets, _o.getUpdatesets())) {
                return false;
            }

            if (!equals(m_queryDefs, _o.getQueryDefs())) {
                return false;
            }

            return equals(m_indexs, _o.getIndexs());
        }

        return false;
    }

    public String getName() {
        return m_name;
    }

    public String getTable() {
        return m_table;
    }

    public String getAlias() {
        return m_alias;
    }

    public List<MemberModel> getMembers() {
        return m_members;
    }

    public VarModel getVar() {
        return m_var;
    }

    public PrimaryKeyModel getPrimaryKey() {
        return m_primaryKey;
    }

    public ReadsetsModel getReadsets() {
        return m_readsets;
    }

    public UpdatesetsModel getUpdatesets() {
        return m_updatesets;
    }

    public List<QueryModel> getQueryDefs() {
        return m_queryDefs;
    }

    public List<IndexModel> getIndexs() {
        return m_indexs;
    }

    public EntityModel setUpdatesets(UpdatesetsModel updatesets) {
        m_updatesets = updatesets;
        return this;
    }

    public EntityModel setReadsets(ReadsetsModel readsets) {
        m_readsets = readsets;
        return this;
    }

    public EntityModel setPrimaryKey(PrimaryKeyModel primaryKey) {
        m_primaryKey = primaryKey;
        return this;
    }

    public EntityModel setVar(VarModel var) {
        m_var = var;
        return this;
    }

    public EntityModel setAlias(String alias) {
        m_alias = alias;
        return this;
    }

    public EntityModel setTable(String table) {
        m_table = table;
        return this;
    }

    public EntityModel setName(String name) {
        m_name = name;
        return this;
    }

}
