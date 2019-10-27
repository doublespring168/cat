package org.unidal.dal.jdbc.datasource;

import java.util.Map;

public interface DataSourceDescriptor {
    String getId();

    String getType();

    Map<String, Object> getProperties();

    String getProperty(String name, String defaultValue);

    boolean getBooleanProperty(String name, boolean defaultValue);

    double getDoubleProperty(String name, double defaultValue);

    int getIntProperty(String name, int defaultValue);

    long getLongProperty(String name, long defaultValue);
}
