package com.example.javatest.service;

import java.util.Collections;
import java.util.List;

public interface CrudService<T> {
    default void save(T input) {
    }
    default T getById(Long id) {
        return null;
    }
    default List<T> getAll() {
        return Collections.emptyList();
    }
    default T update(Long id, T input) {
        return null;
    }
    default void delete(Long id) {
    }
}
