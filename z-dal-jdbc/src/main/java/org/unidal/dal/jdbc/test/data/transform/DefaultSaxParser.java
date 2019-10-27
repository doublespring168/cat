package org.unidal.dal.jdbc.test.data.transform;


import org.unidal.dal.jdbc.test.data.IEntity;
import org.unidal.dal.jdbc.test.data.entity.ColModel;
import org.unidal.dal.jdbc.test.data.entity.DatabaseModel;
import org.unidal.dal.jdbc.test.data.entity.RowModel;
import org.unidal.dal.jdbc.test.data.entity.TableModel;
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

import static org.unidal.dal.jdbc.test.data.Constants.*;

public class DefaultSaxParser extends DefaultHandler {

    private DefaultLinker m_linker = new DefaultLinker(true);

    private DefaultSaxMaker m_maker = new DefaultSaxMaker();

    private Stack<String> m_tags = new Stack<String>();

    private Stack<Object> m_objs = new Stack<Object>();

    private IEntity<?> m_entity;

    private StringBuilder m_text = new StringBuilder();

    public static DatabaseModel parse(InputStream in) throws SAXException, IOException {
        return parse(new InputSource(in));
    }

    public static DatabaseModel parse(InputSource is) throws SAXException, IOException {
        return parseEntity(DatabaseModel.class, is);
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

    public static DatabaseModel parse(Reader reader) throws SAXException, IOException {
        return parse(new InputSource(reader));
    }

    public static DatabaseModel parse(String xml) throws SAXException, IOException {
        return parse(new InputSource(new StringReader(xml)));
    }

    public static <T extends IEntity<?>> T parseEntity(Class<T> type, String xml) throws SAXException, IOException {
        return parseEntity(type, new InputSource(new StringReader(xml)));
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

                if (parent instanceof DatabaseModel) {
                    parseForDatabase((DatabaseModel) parent, tag, qName, attributes);
                } else if (parent instanceof TableModel) {
                    parseForTable((TableModel) parent, tag, qName, attributes);
                } else if (parent instanceof RowModel) {
                    parseForRow((RowModel) parent, tag, qName, attributes);
                } else if (parent instanceof ColModel) {
                    parseForCol((ColModel) parent, tag, qName, attributes);
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

            m_tags.pop();

            if (currentObj instanceof ColModel) {
                ColModel col = (ColModel) currentObj;

                col.setText(getText());
            }
        }

        m_text.setLength(0);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        m_text.append(ch, start, length);
    }

    protected String getText() {
        return m_text.toString();
    }

    private void parseRoot(String qName, Attributes attributes) throws SAXException {
        if (ENTITY_DATABASE.equals(qName)) {
            DatabaseModel database = m_maker.buildDatabase(attributes);

            m_entity = database;
            m_objs.push(database);
            m_tags.push(qName);
        } else if (ENTITY_TABLE.equals(qName)) {
            TableModel table = m_maker.buildTable(attributes);

            m_entity = table;
            m_objs.push(table);
            m_tags.push(qName);
        } else if (ENTITY_ROW.equals(qName)) {
            RowModel row = m_maker.buildRow(attributes);

            m_entity = row;
            m_objs.push(row);
            m_tags.push(qName);
        } else if (ENTITY_COL.equals(qName)) {
            ColModel col = m_maker.buildCol(attributes);

            m_entity = col;
            m_objs.push(col);
            m_tags.push(qName);
        } else {
            throw new SAXException("Unknown root element(" + qName + ") found!");
        }
    }

    private void parseForDatabase(DatabaseModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        if (ENTITY_TABLE.equals(qName)) {
            TableModel table = m_maker.buildTable(attributes);

            m_linker.onTable(parentObj, table);
            m_objs.push(table);
        } else {
            throw new SAXException(String.format("Element(%s) is not expected under database!", qName));
        }

        m_tags.push(qName);
    }

    private void parseForTable(TableModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        if (ENTITY_ROW.equals(qName)) {
            RowModel row = m_maker.buildRow(attributes);

            m_linker.onRow(parentObj, row);
            m_objs.push(row);
        } else {
            throw new SAXException(String.format("Element(%s) is not expected under table!", qName));
        }

        m_tags.push(qName);
    }

    private void parseForRow(RowModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        if (ENTITY_COL.equals(qName)) {
            ColModel col = m_maker.buildCol(attributes);

            m_linker.onCol(parentObj, col);
            m_objs.push(col);
        } else {
            throw new SAXException(String.format("Element(%s) is not expected under row!", qName));
        }

        m_tags.push(qName);
    }

    private void parseForCol(ColModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        m_objs.push(parentObj);
        m_tags.push(qName);
    }
}
