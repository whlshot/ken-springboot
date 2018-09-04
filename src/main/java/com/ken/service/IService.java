package com.ken.service;

import java.util.List;

public interface IService<T> {

    T selectByKey(Object key);

    int save(T entity);

    int delete(Object key);

    int updateAll(T entity);

    int updateNotAll(T entity);

    List<T> selectByExample(Object example);
}
