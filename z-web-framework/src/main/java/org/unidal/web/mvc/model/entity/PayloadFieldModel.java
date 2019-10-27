package org.unidal.web.mvc.model.entity;

import org.unidal.web.mvc.model.BaseEntity;
import org.unidal.web.mvc.model.IVisitor;

public class PayloadFieldModel extends BaseEntity<PayloadFieldModel> {
    private String m_name;

    private String m_format;

    private Boolean m_file;

    private String m_defaultValue;

    private Boolean m_multipleValues;

    private java.lang.reflect.Field m_field;

    private java.lang.reflect.Method m_method;

    public PayloadFieldModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitField(this);
    }

    @Override
    public void mergeAttributes(PayloadFieldModel other) {
        if (other.getName() != null) {
            m_name = other.getName();
        }

        if (other.getFormat() != null) {
            m_format = other.getFormat();
        }

        if (other.getFile() != null) {
            m_file = other.getFile();
        }

        if (other.getDefaultValue() != null) {
            m_defaultValue = other.getDefaultValue();
        }

        if (other.getMultipleValues() != null) {
            m_multipleValues = other.getMultipleValues();
        }
    }

    public java.lang.reflect.Field getField() {
        return m_field;
    }

    public PayloadFieldModel setField(java.lang.reflect.Field field) {
        m_field = field;
        return this;
    }

    public java.lang.reflect.Method getMethod() {
        return m_method;
    }

    public PayloadFieldModel setMethod(java.lang.reflect.Method method) {
        m_method = method;
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_name == null ? 0 : m_name.hashCode());
        hash = hash * 31 + (m_format == null ? 0 : m_format.hashCode());
        hash = hash * 31 + (m_file == null ? 0 : m_file.hashCode());
        hash = hash * 31 + (m_defaultValue == null ? 0 : m_defaultValue.hashCode());
        hash = hash * 31 + (m_multipleValues == null ? 0 : m_multipleValues.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PayloadFieldModel) {
            PayloadFieldModel _o = (PayloadFieldModel) obj;

            if (!equals(m_name, _o.getName())) {
                return false;
            }

            if (!equals(m_format, _o.getFormat())) {
                return false;
            }

            if (!equals(m_file, _o.getFile())) {
                return false;
            }

            if (!equals(m_defaultValue, _o.getDefaultValue())) {
                return false;
            }

            return equals(m_multipleValues, _o.getMultipleValues());
        }

        return false;
    }

    public String getName() {
        return m_name;
    }

    public String getFormat() {
        return m_format;
    }

    public Boolean getFile() {
        return m_file;
    }

    public String getDefaultValue() {
        return m_defaultValue;
    }

    public Boolean getMultipleValues() {
        return m_multipleValues;
    }

    public PayloadFieldModel setDefaultValue(String defaultValue) {
        m_defaultValue = defaultValue;
        return this;
    }

    public PayloadFieldModel setFormat(String format) {
        m_format = format;
        return this;
    }

    public PayloadFieldModel setName(String name) {
        m_name = name;
        return this;
    }

    public boolean isFile() {
        return m_file != null && m_file.booleanValue();
    }

    public PayloadFieldModel setFile(Boolean file) {
        m_file = file;
        return this;
    }

    public boolean isMultipleValues() {
        return m_multipleValues != null && m_multipleValues.booleanValue();
    }

    public PayloadFieldModel setMultipleValues(Boolean multipleValues) {
        m_multipleValues = multipleValues;
        return this;
    }

}
