package org.unidal.dal.jdbc.test.data.transform;

import org.unidal.dal.jdbc.test.data.entity.ColModel;
import org.unidal.dal.jdbc.test.data.entity.DatabaseModel;
import org.unidal.dal.jdbc.test.data.entity.RowModel;
import org.unidal.dal.jdbc.test.data.entity.TableModel;
import org.xml.sax.Attributes;

import static org.unidal.dal.jdbc.test.data.Constants.*;

public class DefaultSaxMaker implements IMaker<Attributes> {

    @Override
    public ColModel buildCol(Attributes attributes) {
        String name = attributes.getValue(ATTR_NAME);
        String key = attributes.getValue(ATTR_KEY);
        ColModel col = new ColModel(name);

        if (key != null) {
            col.setKey(convert(Boolean.class, key, null));
        }

        return col;
    }

    @Override
    public DatabaseModel buildDatabase(Attributes attributes) {
        DatabaseModel database = new DatabaseModel();

        return database;
    }

    @Override
    public RowModel buildRow(Attributes attributes) {
        String status = attributes.getValue(ATTR_STATUS);
        String id = attributes.getValue(ATTR_ID);
        RowModel row = new RowModel(id);

        if (status != null) {
            row.setStatus(status);
        }

        return row;
    }

    @Override
    public TableModel buildTable(Attributes attributes) {
        String name = attributes.getValue(ATTR_NAME);
        TableModel table = new TableModel(name);

        return table;
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
