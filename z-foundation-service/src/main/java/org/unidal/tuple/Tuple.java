package org.unidal.tuple;

public interface Tuple {
    <T> T get(int index);

    int size();
}
