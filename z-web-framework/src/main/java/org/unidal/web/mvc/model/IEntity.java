package org.unidal.web.mvc.model;

public interface IEntity<T> {
    void accept(IVisitor visitor);

    void mergeAttributes(T other);

}
