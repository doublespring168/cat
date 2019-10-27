package org.unidal.dal.jdbc.test.meta;

public interface IEntity<T> {
    void accept(IVisitor visitor);

    void mergeAttributes(T other);

}
