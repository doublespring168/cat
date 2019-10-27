package org.unidal.dal.jdbc.entity;

import org.unidal.dal.jdbc.DataObject;

import java.lang.reflect.Method;

public interface DataObjectNaming {
    Method getGetMethod(Class<? extends DataObject> clazz, String name);

    Method getSetMethod(Class<? extends DataObject> clazz, String name);

}
