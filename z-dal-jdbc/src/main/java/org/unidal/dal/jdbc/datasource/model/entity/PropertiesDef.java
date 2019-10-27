package org.unidal.dal.jdbc.datasource.model.entity;

import org.unidal.dal.jdbc.datasource.model.BaseEntity;
import org.unidal.dal.jdbc.datasource.model.IVisitor;

public class PropertiesDef extends BaseEntity<PropertiesDef> {
    private String m_driver;

    private String m_url;

    private String m_user;

    private String m_password;

    private String m_connectionProperties;

    public PropertiesDef() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitProperties(this);
    }

    @Override
    public void mergeAttributes(PropertiesDef other) {
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_driver == null ? 0 : m_driver.hashCode());
        hash = hash * 31 + (m_url == null ? 0 : m_url.hashCode());
        hash = hash * 31 + (m_user == null ? 0 : m_user.hashCode());
        hash = hash * 31 + (m_password == null ? 0 : m_password.hashCode());
        hash = hash * 31 + (m_connectionProperties == null ? 0 : m_connectionProperties.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PropertiesDef) {
            PropertiesDef _o = (PropertiesDef) obj;

            if (!equals(m_driver, _o.getDriver())) {
                return false;
            }

            if (!equals(m_url, _o.getUrl())) {
                return false;
            }

            if (!equals(m_user, _o.getUser())) {
                return false;
            }

            if (!equals(m_password, _o.getPassword())) {
                return false;
            }

            return equals(m_connectionProperties, _o.getConnectionProperties());
        }

        return false;
    }

    public String getDriver() {
        return m_driver;
    }

    public String getUrl() {
        return m_url;
    }

    public String getUser() {
        return m_user;
    }

    public String getPassword() {
        return m_password;
    }

    public String getConnectionProperties() {
        return m_connectionProperties;
    }

    public PropertiesDef setConnectionProperties(String connectionProperties) {
        m_connectionProperties = connectionProperties;
        return this;
    }

    public PropertiesDef setPassword(String password) {
        m_password = password;
        return this;
    }

    public PropertiesDef setUser(String user) {
        m_user = user;
        return this;
    }

    public PropertiesDef setUrl(String url) {
        m_url = url;
        return this;
    }

    public PropertiesDef setDriver(String driver) {
        m_driver = driver;
        return this;
    }

}
