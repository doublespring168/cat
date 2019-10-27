package org.unidal.dal.jdbc.test.data.transform;

import org.unidal.dal.jdbc.test.data.IEntity;
import org.unidal.dal.jdbc.test.data.IVisitor;
import org.unidal.dal.jdbc.test.data.entity.ColModel;
import org.unidal.dal.jdbc.test.data.entity.DatabaseModel;
import org.unidal.dal.jdbc.test.data.entity.RowModel;
import org.unidal.dal.jdbc.test.data.entity.TableModel;

import static org.unidal.dal.jdbc.test.data.Constants.*;

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

    protected void startTag(String name, boolean closed, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
        startTag(name, null, closed, dynamicAttributes, nameValues);
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
    public void visitCol(ColModel col) {
        startTag(ENTITY_COL, col.getText(), true, null, ATTR_NAME, col.getName(), ATTR_KEY, col.getKey());
    }

    @Override
    public void visitDatabase(DatabaseModel database) {
        startTag(ENTITY_DATABASE, null);

        if (!database.getTables().isEmpty()) {
            for (TableModel table : database.getTables().toArray(new TableModel[0])) {
                table.accept(m_visitor);
            }
        }

        endTag(ENTITY_DATABASE);
    }

    protected void startTag(String name, java.util.Map<String, String> dynamicAttributes, Object... nameValues) {
        startTag(name, null, false, dynamicAttributes, nameValues);
    }

    protected void endTag(String name) {
        m_level--;

        indent();
        m_sb.append("</").append(name).append(">\r\n");
    }

    @Override
    public void visitRow(RowModel row) {
        startTag(ENTITY_ROW, null, ATTR_STATUS, row.getStatus(), ATTR_ID, row.getId());

        if (!row.getCols().isEmpty()) {
            for (ColModel col : row.getCols().toArray(new ColModel[0])) {
                col.accept(m_visitor);
            }
        }

        endTag(ENTITY_ROW);
    }

    @Override
    public void visitTable(TableModel table) {
        startTag(ENTITY_TABLE, null, ATTR_NAME, table.getName());

        if (!table.getRows().isEmpty()) {
            for (RowModel row : table.getRows().toArray(new RowModel[0])) {
                row.accept(m_visitor);
            }
        }

        endTag(ENTITY_TABLE);
    }
}
