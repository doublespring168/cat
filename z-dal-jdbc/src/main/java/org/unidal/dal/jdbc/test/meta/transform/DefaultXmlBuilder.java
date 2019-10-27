package org.unidal.dal.jdbc.test.meta.transform;

import org.unidal.dal.jdbc.test.meta.IEntity;
import org.unidal.dal.jdbc.test.meta.IVisitor;
import org.unidal.dal.jdbc.test.meta.entity.*;

import static org.unidal.dal.jdbc.test.meta.Constants.*;

public class DefaultXmlBuilder implements IVisitor {

    private IVisitor m_visitor = this;

    private int m_level;

    private StringBuilder m_sb;

    private boolean m_compact;

    public DefaultXmlBuilder() {
        this(false);
    }

    public DefaultXmlBuilder(boolean compact) {
        this(compact, new StringBuilder(4096));
    }

    public DefaultXmlBuilder(boolean compact, StringBuilder sb) {
        m_compact = compact;
        m_sb = sb;
        m_sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
    }

    public String buildXml(IEntity<?> entity) {
        entity.accept(m_visitor);
        return m_sb.toString();
    }

    protected void startTag(String name) {
        startTag(name, false, null);
    }

    protected void tagWithText(String name, String text, Object... nameValues) {
        if (text == null) {
            return;
        }

        indent();

        m_sb.append('<').append(name);

        int len = nameValues.length;

        for (int i = 0; i + 1 < len; i += 2) {
            Object attrName = nameValues[i];
            Object attrValue = nameValues[i + 1];

            if (attrValue != null) {
                m_sb.append(' ').append(attrName).append("=\"").append(escape(attrValue)).append('"');
            }
        }

        m_sb.append(">");
        m_sb.append(escape(text, true));
        m_sb.append("</").append(name).append(">\r\n");
    }

    protected void indent() {
        if (!m_compact) {
            for (int i = m_level - 1; i >= 0; i--) {
                m_sb.append("   ");
            }
        }
    }

    protected String escape(Object value) {
        return escape(value, false);
    }

    protected String escape(Object value, boolean text) {
        if (value == null) {
            return null;
        }

        String str = value.toString();
        int len = str.length();
        StringBuilder sb = new StringBuilder(len + 16);

        for (int i = 0; i < len; i++) {
            final char ch = str.charAt(i);

            switch (ch) {
                case '<':
                    sb.append("&lt;");
                    break;
                case '>':
                    sb.append("&gt;");
                    break;
                case '&':
                    sb.append("&amp;");
                    break;
                case '"':
                    if (!text) {
                        sb.append("&quot;");
                        break;
                    }
                default:
                    sb.append(ch);
                    break;
            }
        }

        return sb.toString();
    }

    @Override
    public void visitEntities(EntitiesModel entities) {
        startTag(ENTITY_ENTITIES, null);

        if (!entities.getEntities().isEmpty()) {
            for (EntityModel entity : entities.getEntities().toArray(new EntityModel[0])) {
                entity.accept(m_visitor);
            }
        }

        endTag(ENTITY_ENTITIES);
    }

    protected void startTag(String name, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
        startTag(name, null, false, dynamicAttributes, nameValues);
    }

    protected void endTag(String name) {
        m_level--;

        indent();
        m_sb.append("</").append(name).append(">\r\n");
    }

    protected void startTag(String name, Object text, boolean closed, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
        indent();

        m_sb.append('<').append(name);

        int len = nameValues.length;

        for (int i = 0; i + 1 < len; i += 2) {
            Object attrName = nameValues[i];
            Object attrValue = nameValues[i + 1];

            if (attrValue != null) {
                m_sb.append(' ').append(attrName).append("=\"").append(escape(attrValue)).append('"');
            }
        }

        if (dynamicAttributes != null) {
            for (java.util.Map.Entry<String, String> e : dynamicAttributes.entrySet()) {
                m_sb.append(' ').append(e.getKey()).append("=\"").append(escape(e.getValue())).append('"');
            }
        }

        if (text != null && closed) {
            m_sb.append('>');
            m_sb.append(escape(text, true));
            m_sb.append("</").append(name).append(">\r\n");
        } else {
            if (closed) {
                m_sb.append('/');
            } else {
                m_level++;
            }

            m_sb.append(">\r\n");
        }
    }

    @Override
    public void visitEntity(EntityModel entity) {
        startTag(ENTITY_ENTITY, null, ATTR_NAME, entity.getName(), ATTR_TABLE, entity.getTable(), ATTR_ALIAS, entity.getAlias());

        if (!entity.getMembers().isEmpty()) {
            for (MemberModel member : entity.getMembers().toArray(new MemberModel[0])) {
                member.accept(m_visitor);
            }
        }

        if (entity.getVar() != null) {
            entity.getVar().accept(m_visitor);
        }

        if (entity.getPrimaryKey() != null) {
            entity.getPrimaryKey().accept(m_visitor);
        }

        if (entity.getReadsets() != null) {
            entity.getReadsets().accept(m_visitor);
        }

        if (entity.getUpdatesets() != null) {
            entity.getUpdatesets().accept(m_visitor);
        }

        if (!entity.getQueryDefs().isEmpty()) {
            startTag(ENTITY_QUERY_DEFS);

            for (QueryModel query : entity.getQueryDefs().toArray(new QueryModel[0])) {
                query.accept(m_visitor);
            }

            endTag(ENTITY_QUERY_DEFS);
        }

        if (!entity.getIndexs().isEmpty()) {
            for (IndexModel index : entity.getIndexs().toArray(new IndexModel[0])) {
                index.accept(m_visitor);
            }
        }

        endTag(ENTITY_ENTITY);
    }

    @Override
    public void visitIndex(IndexModel index) {
        startTag(ENTITY_INDEX, true, null, ATTR_NAME, index.getName(), ATTR_MEMBERS, index.getMembers(), ATTR_UNIQUE, index.getUnique());
    }

    protected void startTag(String name, boolean closed, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
        startTag(name, null, closed, dynamicAttributes, nameValues);
    }

    @Override
    public void visitMember(MemberModel member) {
        startTag(ENTITY_MEMBER, true, null, ATTR_NAME, member.getName(), ATTR_FIELD, member.getField(), ATTR_VALUE_TYPE, member.getValueType(), ATTR_LENGTH, member.getLength(), ATTR_NULLABLE, member.getNullable(), ATTR_KEY, member.getKey(), ATTR_AUTO_INCREMENT, member.getAutoIncrement(), ATTR_INSERT_EXPR, member.getInsertExpr(), ATTR_UPDATE_EXPR, member.getUpdateExpr());
    }

    @Override
    public void visitParam(ParamModel param) {
        startTag(ENTITY_PARAM, true, null, ATTR_NAME, param.getName());
    }

    @Override
    public void visitPrimaryKey(PrimaryKeyModel primaryKey) {
        startTag(ENTITY_PRIMARY_KEY, true, null, ATTR_NAME, primaryKey.getName(), ATTR_MEMBERS, primaryKey.getMembers());
    }

    @Override
    public void visitQuery(QueryModel query) {
        startTag(ENTITY_QUERY, null, ATTR_NAME, query.getName(), ATTR_TYPE, query.getType());

        element(ELEMENT_STATEMENT, query.getStatement(), true);

        if (query.getParam() != null) {
            query.getParam().accept(m_visitor);
        }

        endTag(ENTITY_QUERY);
    }

    protected void element(String name, String text, boolean escape) {
        if (text == null) {
            return;
        }

        indent();

        m_sb.append('<').append(name).append(">");

        if (escape) {
            m_sb.append(escape(text, true));
        } else {
            m_sb.append("<![CDATA[").append(text).append("]]>");
        }

        m_sb.append("</").append(name).append(">\r\n");
    }

    @Override
    public void visitReadset(ReadsetModel readset) {
        startTag(ENTITY_READSET, true, null, ATTR_NAME, readset.getName(), ATTR_ALL, readset.getAll());
    }

    @Override
    public void visitReadsets(ReadsetsModel readsets) {
        startTag(ENTITY_READSETS, null);

        if (readsets.getReadset() != null) {
            readsets.getReadset().accept(m_visitor);
        }

        endTag(ENTITY_READSETS);
    }

    @Override
    public void visitUpdateset(UpdatesetModel updateset) {
        startTag(ENTITY_UPDATESET, true, null, ATTR_NAME, updateset.getName(), ATTR_ALL, updateset.getAll());
    }

    @Override
    public void visitUpdatesets(UpdatesetsModel updatesets) {
        startTag(ENTITY_UPDATESETS, null);

        if (updatesets.getUpdateset() != null) {
            updatesets.getUpdateset().accept(m_visitor);
        }

        endTag(ENTITY_UPDATESETS);
    }

    @Override
    public void visitVar(VarModel var) {
        startTag(ENTITY_VAR, true, null, ATTR_NAME, var.getName(), ATTR_VALUE_TYPE, var.getValueType(), ATTR_KEY_MEMBER, var.getKeyMember());
    }
}
