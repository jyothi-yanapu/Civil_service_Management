package com.jyothiyanapu.csms.dao;

import java.util.List;

public interface GenericDao<T> {

    void save(T entity);

    T findById(int id);

    List<T> findAll();

    boolean deleteById(int id);

    void update(T entity);
}
