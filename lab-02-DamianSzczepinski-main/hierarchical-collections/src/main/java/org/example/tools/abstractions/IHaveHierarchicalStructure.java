package org.example.model.abstraction;

import java.util.List;

public interface IHaveHierarchicalStructure<T> {

    void setParent(T parent);

    List<T> getChildren();

    T getParent();

    int getId();

    Integer getParentId();
}
