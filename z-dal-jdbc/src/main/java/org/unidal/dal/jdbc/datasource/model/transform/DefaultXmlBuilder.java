package org.unidal.dal.jdbc.datasource.model.transform;

import org.unidal.dal.jdbc.datasource.model.IEntity;
import org.unidal.dal.jdbc.datasource.model.IVisitor;
import org.unidal.dal.jdbc.datasource.model.entity.DataSourceDef;
import org.unidal.dal.jdbc.datasource.model.entity.DataSourcesDef;
import org.unidal.dal.jdbc.datasource.model.entity.PropertiesDef;

import static org.unidal.dal.jdbc.datasource.model.Constants.*;

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

    @Override
    public void visitDataSource(DataSourceDef dataSource) {
        startTag(ENTITY_DATA_SOURCE, null, ATTR_ID, dataSource.getId(), ATTR_TYPE, dataSource.getType());

        tagWithText(ELEMENT_MINIMUM_POOL_SIZE, dataSource.getMinimumPoolSize() == null ? null : String.valueOf(dataSource.getMinimumPoolSize()));

        tagWithText(ELEMENT_MAXIMUM_POOL_SIZE, dataSource.getMaximumPoolSize() == null ? null : String.valueOf(dataSource.getMaximumPoolSize()));

        element(ELEMENT_CONNECTION_TIMEOUT, dataSource.getConnectionTimeout(), true);

        element(ELEMENT_IDLE_TIMEOUT, dataSource.getIdleTimeout(), true);

        tagWithText(ELEMENT_STATEMENT_CACHE_SIZE, dataSource.getStatementCacheSize() == null ? null : String.valueOf(dataSource.getStatementCacheSize()));

        if (dataSource.getProperties() != null) {
            dataSource.getProperties().accept(m_visitor);
        }

        endTag(ENTITY_DATA_SOURCE);
    }

    @Override
    public void visitDataSources(DataSourcesDef dataSources) {
        startTag(ENTITY_DATA_SOURCES, null);

        if (!dataSources.getDataSourcesMap().isEmpty()) {
            for (DataSourceDef dataSource : dataSources.getDataSourcesMap().values().toArray(new DataSourceDef[0])) {
                dataSource.accept(m_visitor);
            }
        }

        endTag(ENTITY_DATA_SOURCES);
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
    public void visitProperties(PropertiesDef properties) {
        startTag(ENTITY_PROPERTIES, null);

        element(ELEMENT_DRIVER, properties.getDriver(), true);

        element(ELEMENT_URL, properties.getUrl(), true);

        element(ELEMENT_USER, properties.getUser(), true);

        element(ELEMENT_PASSWORD, properties.getPassword(), true);

        element(ELEMENT_CONNECTIONPROPERTIES, properties.getConnectionProperties(), true);

        endTag(ENTITY_PROPERTIES);
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
}
