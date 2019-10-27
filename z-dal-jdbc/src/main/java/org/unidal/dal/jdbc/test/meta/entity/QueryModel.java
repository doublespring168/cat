package org.unidal.dal.jdbc.test.meta.entity;

import org.unidal.dal.jdbc.test.meta.BaseEntity;
import org.unidal.dal.jdbc.test.meta.IVisitor;

public class QueryModel extends BaseEntity<QueryModel> {
    private String m_name;

    private String m_type;

    private String m_statement;

    private ParamModel m_param;

    public QueryModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitQuery(this);
    }

    @Override
    public void mergeAttributes(QueryModel other) {
        if (other.getName() != null) {
            m_name = other.getName();
        }

        if (other.getType() != null) {
            m_type = other.getType();
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
        hash = hash * 31 + (m_type == null ? 0 : m_type.hashCode());
        hash = hash * 31 + (m_statement == null ? 0 : m_statement.hashCode());
        hash = hash * 31 + (m_param == null ? 0 : m_param.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof QueryModel) {
            QueryModel _o = (QueryModel) obj;

            if (!equals(m_name, _o.getName())) {
                return false;
            }

            if (!equals(m_type, _o.getType())) {
                return false;
            }

            if (!equals(m_statement, _o.getStatement())) {
                return false;
            }

            return equals(m_param, _o.getParam());
        }

        return false;
    }

    public String getName() {
        return m_name;
    }

    public String getType() {
        return m_type;
    }

    public String getStatement() {
        return m_statement;
    }

    public ParamModel getParam() {
        return m_param;
    }

    public QueryModel setParam(ParamModel param) {
        m_param = param;
        return this;
    }

    public QueryModel setStatement(String statement) {
        m_statement = statement;
        return this;
    }

    public QueryModel setType(String type) {
        m_type = type;
        return this;
    }

    public QueryModel setName(String name) {
        m_name = name;
        return this;
    }

}
