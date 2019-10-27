package org.unidal.dal.jdbc.datasource.model.transform;

import org.unidal.dal.jdbc.datasource.model.entity.DataSourceDef;
import org.unidal.dal.jdbc.datasource.model.entity.DataSourcesDef;
import org.unidal.dal.jdbc.datasource.model.entity.PropertiesDef;
import org.xml.sax.Attributes;

import static org.unidal.dal.jdbc.datasource.model.Constants.ATTR_ID;
import static org.unidal.dal.jdbc.datasource.model.Constants.ATTR_TYPE;

public class DefaultSaxMaker implements IMaker<Attributes> {

    @Override
    public DataSourceDef buildDataSource(Attributes attributes) {
        String id = attributes.getValue(ATTR_ID);
        String type = attributes.getValue(ATTR_TYPE);
        DataSourceDef dataSource = new DataSourceDef(id);

        if (type != null) {
            dataSource.setType(type);
        }

        return dataSource;
    }

    @Override
    public DataSourcesDef buildDataSources(Attributes attributes) {
        DataSourcesDef dataSources = new DataSourcesDef();

        return dataSources;
    }

    @Override
    public PropertiesDef buildProperties(Attributes attributes) {
        PropertiesDef properties = new PropertiesDef();

        return properties;
    }

    @SuppressWarnings("unchecked")
    protected <T> T convert(Class<T> type, String value, T defaultValue) {
        if (value == null) {
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
}
