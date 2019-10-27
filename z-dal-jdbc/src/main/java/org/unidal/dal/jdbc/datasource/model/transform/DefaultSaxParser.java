package org.unidal.dal.jdbc.datasource.model.transform;

import org.unidal.dal.jdbc.datasource.model.IEntity;
import org.unidal.dal.jdbc.datasource.model.entity.DataSourceDef;
import org.unidal.dal.jdbc.datasource.model.entity.DataSourcesDef;
import org.unidal.dal.jdbc.datasource.model.entity.PropertiesDef;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Stack;

import static org.unidal.dal.jdbc.datasource.model.Constants.*;

public class DefaultSaxParser extends DefaultHandler {

    private DefaultLinker m_linker = new DefaultLinker(true);

    private DefaultSaxMaker m_maker = new DefaultSaxMaker();

    private Stack<String> m_tags = new Stack<String>();

    private Stack<Object> m_objs = new Stack<Object>();

    private IEntity<?> m_entity;

    private StringBuilder m_text = new StringBuilder();

    public static DataSourcesDef parse(InputStream in) throws SAXException, IOException {
        return parse(new InputSource(in));
    }

    public static DataSourcesDef parse(InputSource is) throws SAXException, IOException {
        return parseEntity(DataSourcesDef.class, is);
    }

    @SuppressWarnings("unchecked")
    public static <T extends IEntity<?>> T parseEntity(Class<T> type, InputSource is) throws SAXException, IOException {
        try {
            DefaultSaxParser handler = new DefaultSaxParser();
            SAXParserFactory factory = SAXParserFactory.newInstance();

            factory.setValidating(false);
            factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            factory.setFeature("http://xml.org/sax/features/validation", false);

            factory.newSAXParser().parse(is, handler);
            return (T) handler.getEntity();
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException("Unable to get SAX parser instance!", e);
        }
    }

    private IEntity<?> getEntity() {
        return m_entity;
    }

    public static DataSourcesDef parse(Reader reader) throws SAXException, IOException {
        return parse(new InputSource(reader));
    }

    public static DataSourcesDef parse(String xml) throws SAXException, IOException {
        return parse(new InputSource(new StringReader(xml)));
    }

    public static <T extends IEntity<?>> T parseEntity(Class<T> type, String xml) throws SAXException, IOException {
        return parseEntity(type, new InputSource(new StringReader(xml)));
    }

    @Override
    public void endDocument() throws SAXException {
        m_linker.finish();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (uri == null || uri.length() == 0) {
            if (m_objs.isEmpty()) { // root
                parseRoot(qName, attributes);
            } else {
                Object parent = m_objs.peek();
                String tag = m_tags.peek();

                if (parent instanceof DataSourcesDef) {
                    parseForDataSources((DataSourcesDef) parent, tag, qName, attributes);
                } else if (parent instanceof DataSourceDef) {
                    parseForDataSource((DataSourceDef) parent, tag, qName, attributes);
                } else if (parent instanceof PropertiesDef) {
                    parseForProperties((PropertiesDef) parent, tag, qName, attributes);
                } else {
                    throw new RuntimeException(String.format("Unknown entity(%s) under %s!", qName, parent.getClass().getName()));
                }
            }

            m_text.setLength(0);
        } else {
            throw new SAXException(String.format("Namespace(%s) is not supported by %s.", uri, this.getClass().getName()));
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (uri == null || uri.length() == 0) {
            Object currentObj = m_objs.pop();
            String currentTag = m_tags.pop();

            if (currentObj instanceof DataSourceDef) {
                DataSourceDef dataSource = (DataSourceDef) currentObj;

                if (ELEMENT_MINIMUM_POOL_SIZE.equals(currentTag)) {
                    dataSource.setMinimumPoolSize(convert(Integer.class, getText(), null));
                } else if (ELEMENT_MAXIMUM_POOL_SIZE.equals(currentTag)) {
                    dataSource.setMaximumPoolSize(convert(Integer.class, getText(), null));
                } else if (ELEMENT_CONNECTION_TIMEOUT.equals(currentTag)) {
                    dataSource.setConnectionTimeout(getText());
                } else if (ELEMENT_IDLE_TIMEOUT.equals(currentTag)) {
                    dataSource.setIdleTimeout(getText());
                } else if (ELEMENT_STATEMENT_CACHE_SIZE.equals(currentTag)) {
                    dataSource.setStatementCacheSize(convert(Integer.class, getText(), null));
                }
            } else if (currentObj instanceof PropertiesDef) {
                PropertiesDef properties = (PropertiesDef) currentObj;

                if (ELEMENT_DRIVER.equals(currentTag)) {
                    properties.setDriver(getText());
                } else if (ELEMENT_URL.equals(currentTag)) {
                    properties.setUrl(getText());
                } else if (ELEMENT_USER.equals(currentTag)) {
                    properties.setUser(getText());
                } else if (ELEMENT_PASSWORD.equals(currentTag)) {
                    properties.setPassword(getText());
                } else if (ELEMENT_CONNECTIONPROPERTIES.equals(currentTag)) {
                    properties.setConnectionProperties(getText());
                }
            }
        }

        m_text.setLength(0);
    }

    @SuppressWarnings("unchecked")
    protected <T> T convert(Class<T> type, String value, T defaultValue) {
        if (value == null || value.length() == 0) {
            return defaultValue;
        }

        if (type == Boolean.class) {
            return (T) Boolean.valueOf(value);
        } else if (type == Integer.class) {
            return (T) Integer.valueOf(value);
        } else if (type == Long.class) {
            return (T) Long.valueOf(value);
        } else if (type == Short.class) {
            return (T) Short.valueOf(value);
        } else if (type == Float.class) {
            return (T) Float.valueOf(value);
        } else if (type == Double.class) {
            return (T) Double.valueOf(value);
        } else if (type == Byte.class) {
            return (T) Byte.valueOf(value);
        } else if (type == Character.class) {
            return (T) (Character) value.charAt(0);
        } else {
            return (T) value;
        }
    }

    protected String getText() {
        return m_text.toString();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        m_text.append(ch, start, length);
    }

    private void parseRoot(String qName, Attributes attributes) throws SAXException {
        if (ENTITY_DATA_SOURCES.equals(qName)) {
            DataSourcesDef dataSources = m_maker.buildDataSources(attributes);

            m_entity = dataSources;
            m_objs.push(dataSources);
            m_tags.push(qName);
        } else if (ENTITY_DATA_SOURCE.equals(qName)) {
            DataSourceDef dataSource = m_maker.buildDataSource(attributes);

            m_entity = dataSource;
            m_objs.push(dataSource);
            m_tags.push(qName);
        } else if (ENTITY_PROPERTIES.equals(qName)) {
            PropertiesDef properties = m_maker.buildProperties(attributes);

            m_entity = properties;
            m_objs.push(properties);
            m_tags.push(qName);
        } else {
            throw new SAXException("Unknown root element(" + qName + ") found!");
        }
    }

    private void parseForDataSources(DataSourcesDef parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        if (ENTITY_DATA_SOURCE.equals(qName)) {
            DataSourceDef dataSource = m_maker.buildDataSource(attributes);

            m_linker.onDataSource(parentObj, dataSource);
            m_objs.push(dataSource);
        } else {
            throw new SAXException(String.format("Element(%s) is not expected under data-sources!", qName));
        }

        m_tags.push(qName);
    }

    private void parseForDataSource(DataSourceDef parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        if (ELEMENT_MINIMUM_POOL_SIZE.equals(qName) || ELEMENT_MAXIMUM_POOL_SIZE.equals(qName) || ELEMENT_CONNECTION_TIMEOUT.equals(qName) || ELEMENT_IDLE_TIMEOUT.equals(qName) || ELEMENT_STATEMENT_CACHE_SIZE.equals(qName)) {
            m_objs.push(parentObj);
        } else if (ENTITY_PROPERTIES.equals(qName)) {
            PropertiesDef properties = m_maker.buildProperties(attributes);

            m_linker.onProperties(parentObj, properties);
            m_objs.push(properties);
        } else {
            throw new SAXException(String.format("Element(%s) is not expected under data-source!", qName));
        }

        m_tags.push(qName);
    }

    private void parseForProperties(PropertiesDef parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        if (ELEMENT_DRIVER.equals(qName) || ELEMENT_URL.equals(qName) || ELEMENT_USER.equals(qName) || ELEMENT_PASSWORD.equals(qName) || ELEMENT_CONNECTIONPROPERTIES.equals(qName)) {
            m_objs.push(parentObj);
        } else {
            throw new SAXException(String.format("Element(%s) is not expected under properties!", qName));
        }

        m_tags.push(qName);
    }
}
