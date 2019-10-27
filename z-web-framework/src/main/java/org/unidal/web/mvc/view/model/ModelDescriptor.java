package org.unidal.web.mvc.view.model;

import java.lang.reflect.Field;
import java.util.List;

public interface ModelDescriptor {
    String getModelName();

    List<Field> getAttributeFields();

    List<Field> getElementFields();

    List<Field> getEntityFields();

    List<Field> getPojoFields();
}
