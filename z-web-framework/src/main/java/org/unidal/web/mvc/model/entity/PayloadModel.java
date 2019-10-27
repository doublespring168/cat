package org.unidal.web.mvc.model.entity;

import org.unidal.web.mvc.model.BaseEntity;
import org.unidal.web.mvc.model.IVisitor;

import java.util.ArrayList;
import java.util.List;

public class PayloadModel extends BaseEntity<PayloadModel> {
    private Class<?> m_payloadClass;

    private List<PayloadFieldModel> m_fields = new ArrayList<PayloadFieldModel>();

    private List<PayloadPathModel> m_paths = new ArrayList<PayloadPathModel>();

    private List<PayloadObjectModel> m_objects = new ArrayList<PayloadObjectModel>();

    public PayloadModel() {
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitPayload(this);
    }

    @Override
    public void mergeAttributes(PayloadModel other) {
        if (other.getPayloadClass() != null) {
            m_payloadClass = other.getPayloadClass();
        }
    }

    public PayloadModel addField(PayloadFieldModel field) {
        m_fields.add(field);
        return this;
    }

    public PayloadModel addObject(PayloadObjectModel object) {
        m_objects.add(object);
        return this;
    }

    public PayloadModel addPath(PayloadPathModel path) {
        m_paths.add(path);
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 0;

        hash = hash * 31 + (m_payloadClass == null ? 0 : m_payloadClass.hashCode());
        hash = hash * 31 + (m_fields == null ? 0 : m_fields.hashCode());
        hash = hash * 31 + (m_paths == null ? 0 : m_paths.hashCode());
        hash = hash * 31 + (m_objects == null ? 0 : m_objects.hashCode());

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PayloadModel) {
            PayloadModel _o = (PayloadModel) obj;

            if (!equals(m_payloadClass, _o.getPayloadClass())) {
                return false;
            }

            if (!equals(m_fields, _o.getFields())) {
                return false;
            }

            if (!equals(m_paths, _o.getPaths())) {
                return false;
            }

            return equals(m_objects, _o.getObjects());
        }

        return false;
    }

    public Class<?> getPayloadClass() {
        return m_payloadClass;
    }

    public List<PayloadFieldModel> getFields() {
        return m_fields;
    }

    public List<PayloadPathModel> getPaths() {
        return m_paths;
    }

    public List<PayloadObjectModel> getObjects() {
        return m_objects;
    }

    public PayloadModel setPayloadClass(Class<?> payloadClass) {
        m_payloadClass = payloadClass;
        return this;
    }

}
