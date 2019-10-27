package org.unidal.dal.jdbc.entity;

import org.unidal.dal.jdbc.DataField;
import org.unidal.dal.jdbc.DataObject;

public interface DataObjectAccessor {
    <T extends DataObject> Object getFieldValue(T dataObject, DataField dataField);

    <T extends DataObject> T newInstance(Class<T> clazz);

    <T extends DataObject> void setFieldValue(T row, DataField field, Object value);
}
