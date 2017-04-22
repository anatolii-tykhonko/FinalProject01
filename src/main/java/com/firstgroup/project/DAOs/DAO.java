package com.firstgroup.project.DAOs;

/**
 * Created by Sonikb on 22.04.2017.
 */
public interface DAO<T> {

    T save(T obj);

    boolean delete(T obj);

    T update(T obj);
}
