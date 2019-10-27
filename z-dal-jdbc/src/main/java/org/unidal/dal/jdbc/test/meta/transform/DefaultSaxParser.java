package org.unidal.dal.jdbc.test.meta.transform;

import org.unidal.dal.jdbc.test.meta.IEntity;
import org.unidal.dal.jdbc.test.meta.entity.*;
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

import static org.unidal.dal.jdbc.test.meta.Constants.*;

public class DefaultSaxParser extends DefaultHandler {

    private DefaultLinker m_linker = new DefaultLinker(true);

    private DefaultSaxMaker m_maker = new DefaultSaxMaker();

    private Stack<String> m_tags = new Stack<String>();

    private Stack<Object> m_objs = new Stack<Object>();

    private IEntity<?> m_entity;

    private StringBuilder m_text = new StringBuilder();

    public static EntitiesModel parse(InputStream in) throws SAXException, IOException {
        return parse(new InputSource(in));
    }

    public static EntitiesModel parse(InputSource is) throws SAXException, IOException {
        return parseEntity(EntitiesModel.class, is);
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

    public static EntitiesModel parse(Reader reader) throws SAXException, IOException {
        return parse(new InputSource(reader));
    }

    public static EntitiesModel parse(String xml) throws SAXException, IOException {
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

                if (parent instanceof EntitiesModel) {
                    parseForEntities((EntitiesModel) parent, tag, qName, attributes);
                } else if (parent instanceof EntityModel) {
                    parseForEntity((EntityModel) parent, tag, qName, attributes);
                } else if (parent instanceof MemberModel) {
                    parseForMember((MemberModel) parent, tag, qName, attributes);
                } else if (parent instanceof VarModel) {
                    parseForVar((VarModel) parent, tag, qName, attributes);
                } else if (parent instanceof PrimaryKeyModel) {
                    parseForPrimaryKey((PrimaryKeyModel) parent, tag, qName, attributes);
                } else if (parent instanceof ReadsetsModel) {
                    parseForReadsets((ReadsetsModel) parent, tag, qName, attributes);
                } else if (parent instanceof ReadsetModel) {
                    parseForReadset((ReadsetModel) parent, tag, qName, attributes);
                } else if (parent instanceof UpdatesetsModel) {
                    parseForUpdatesets((UpdatesetsModel) parent, tag, qName, attributes);
                } else if (parent instanceof UpdatesetModel) {
                    parseForUpdateset((UpdatesetModel) parent, tag, qName, attributes);
                } else if (parent instanceof QueryModel) {
                    parseForQuery((QueryModel) parent, tag, qName, attributes);
                } else if (parent instanceof ParamModel) {
                    parseForParam((ParamModel) parent, tag, qName, attributes);
                } else if (parent instanceof IndexModel) {
                    parseForIndex((IndexModel) parent, tag, qName, attributes);
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

            if (currentObj instanceof QueryModel) {
                QueryModel query = (QueryModel) currentObj;

                if (ELEMENT_STATEMENT.equals(currentTag)) {
                    query.setStatement(getText());
                }
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
        if (ENTITY_ENTITIES.equals(qName)) {
            EntitiesModel entities = m_maker.buildEntities(attributes);

            m_entity = entities;
            m_objs.push(entities);
            m_tags.push(qName);
        } else if (ENTITY_ENTITY.equals(qName)) {
            EntityModel entity = m_maker.buildEntity(attributes);

            m_entity = entity;
            m_objs.push(entity);
            m_tags.push(qName);
        } else if (ENTITY_MEMBER.equals(qName)) {
            MemberModel member = m_maker.buildMember(attributes);

            m_entity = member;
            m_objs.push(member);
            m_tags.push(qName);
        } else if (ENTITY_VAR.equals(qName)) {
            VarModel var = m_maker.buildVar(attributes);

            m_entity = var;
            m_objs.push(var);
            m_tags.push(qName);
        } else if (ENTITY_PRIMARY_KEY.equals(qName)) {
            PrimaryKeyModel primaryKey = m_maker.buildPrimaryKey(attributes);

            m_entity = primaryKey;
            m_objs.push(primaryKey);
            m_tags.push(qName);
        } else if (ENTITY_READSETS.equals(qName)) {
            ReadsetsModel readsets = m_maker.buildReadsets(attributes);

            m_entity = readsets;
            m_objs.push(readsets);
            m_tags.push(qName);
        } else if (ENTITY_READSET.equals(qName)) {
            ReadsetModel readset = m_maker.buildReadset(attributes);

            m_entity = readset;
            m_objs.push(readset);
            m_tags.push(qName);
        } else if (ENTITY_UPDATESETS.equals(qName)) {
            UpdatesetsModel updatesets = m_maker.buildUpdatesets(attributes);

            m_entity = updatesets;
            m_objs.push(updatesets);
            m_tags.push(qName);
        } else if (ENTITY_UPDATESET.equals(qName)) {
            UpdatesetModel updateset = m_maker.buildUpdateset(attributes);

            m_entity = updateset;
            m_objs.push(updateset);
            m_tags.push(qName);
        } else if (ENTITY_QUERY.equals(qName)) {
            QueryModel query = m_maker.buildQuery(attributes);

            m_entity = query;
            m_objs.push(query);
            m_tags.push(qName);
        } else if (ENTITY_PARAM.equals(qName)) {
            ParamModel param = m_maker.buildParam(attributes);

            m_entity = param;
            m_objs.push(param);
            m_tags.push(qName);
        } else if (ENTITY_INDEX.equals(qName)) {
            IndexModel index = m_maker.buildIndex(attributes);

            m_entity = index;
            m_objs.push(index);
            m_tags.push(qName);
        } else {
            throw new SAXException("Unknown root element(" + qName + ") found!");
        }
    }

    private void parseForEntities(EntitiesModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        if (ENTITY_ENTITY.equals(qName)) {
            EntityModel entity = m_maker.buildEntity(attributes);

            m_linker.onEntity(parentObj, entity);
            m_objs.push(entity);
        } else {
            throw new SAXException(String.format("Element(%s) is not expected under entities!", qName));
        }

        m_tags.push(qName);
    }

    private void parseForEntity(EntityModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        if (ENTITY_QUERY_DEFS.equals(qName)) {
            m_objs.push(parentObj);
        } else if (ENTITY_MEMBER.equals(qName)) {
            MemberModel member = m_maker.buildMember(attributes);

            m_linker.onMember(parentObj, member);
            m_objs.push(member);
        } else if (ENTITY_VAR.equals(qName)) {
            VarModel var = m_maker.buildVar(attributes);

            m_linker.onVar(parentObj, var);
            m_objs.push(var);
        } else if (ENTITY_PRIMARY_KEY.equals(qName)) {
            PrimaryKeyModel primaryKey = m_maker.buildPrimaryKey(attributes);

            m_linker.onPrimaryKey(parentObj, primaryKey);
            m_objs.push(primaryKey);
        } else if (ENTITY_READSETS.equals(qName)) {
            ReadsetsModel readsets = m_maker.buildReadsets(attributes);

            m_linker.onReadsets(parentObj, readsets);
            m_objs.push(readsets);
        } else if (ENTITY_UPDATESETS.equals(qName)) {
            UpdatesetsModel updatesets = m_maker.buildUpdatesets(attributes);

            m_linker.onUpdatesets(parentObj, updatesets);
            m_objs.push(updatesets);
        } else if (ENTITY_QUERY.equals(qName)) {
            QueryModel query = m_maker.buildQuery(attributes);

            m_linker.onQuery(parentObj, query);
            m_objs.push(query);
        } else if (ENTITY_INDEX.equals(qName)) {
            IndexModel index = m_maker.buildIndex(attributes);

            m_linker.onIndex(parentObj, index);
            m_objs.push(index);
        } else {
            throw new SAXException(String.format("Element(%s) is not expected under entity!", qName));
        }

        m_tags.push(qName);
    }

    private void parseForMember(MemberModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        m_objs.push(parentObj);
        m_tags.push(qName);
    }

    private void parseForVar(VarModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        m_objs.push(parentObj);
        m_tags.push(qName);
    }

    private void parseForPrimaryKey(PrimaryKeyModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        m_objs.push(parentObj);
        m_tags.push(qName);
    }

    private void parseForReadsets(ReadsetsModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        if (ENTITY_READSET.equals(qName)) {
            ReadsetModel readset = m_maker.buildReadset(attributes);

            m_linker.onReadset(parentObj, readset);
            m_objs.push(readset);
        } else {
            throw new SAXException(String.format("Element(%s) is not expected under readsets!", qName));
        }

        m_tags.push(qName);
    }

    private void parseForReadset(ReadsetModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        m_objs.push(parentObj);
        m_tags.push(qName);
    }

    private void parseForUpdatesets(UpdatesetsModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        if (ENTITY_UPDATESET.equals(qName)) {
            UpdatesetModel updateset = m_maker.buildUpdateset(attributes);

            m_linker.onUpdateset(parentObj, updateset);
            m_objs.push(updateset);
        } else {
            throw new SAXException(String.format("Element(%s) is not expected under updatesets!", qName));
        }

        m_tags.push(qName);
    }

    private void parseForUpdateset(UpdatesetModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        m_objs.push(parentObj);
        m_tags.push(qName);
    }

    private void parseForQuery(QueryModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        if (ELEMENT_STATEMENT.equals(qName)) {
            m_objs.push(parentObj);
        } else if (ENTITY_PARAM.equals(qName)) {
            ParamModel param = m_maker.buildParam(attributes);

            m_linker.onParam(parentObj, param);
            m_objs.push(param);
        } else {
            throw new SAXException(String.format("Element(%s) is not expected under query!", qName));
        }

        m_tags.push(qName);
    }

    private void parseForParam(ParamModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        m_objs.push(parentObj);
        m_tags.push(qName);
    }

    private void parseForIndex(IndexModel parentObj, String parentTag, String qName, Attributes attributes) throws SAXException {
        m_objs.push(parentObj);
        m_tags.push(qName);
    }
}
