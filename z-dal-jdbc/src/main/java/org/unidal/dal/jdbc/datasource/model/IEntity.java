package org.unidal.dal.jdbc.datasource.model;

public interface IEntity<T> {
    void accept(IVisitor visitor);

    void mergeAttributes(T other);

}
