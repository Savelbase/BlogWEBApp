package org.itstep.dao;

import java.util.List;

public interface GenericDAO<T, ID> {
    ID save(T data);

    List<T> findAll();

    T findById(ID id);

    void delete(ID id);

    T update(T data);
}