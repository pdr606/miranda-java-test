package com.example.javatest.service.generic;

import java.util.Collections;
import java.util.List;

public interface CrudService<T, C , U> {

    default T getById(Long id) {
        return null;
    }

    default T update(Long id, U updateDto) {
        return null;
    }

    default void save(C createDto) {
    }
    default void delete(Long id) {
    }

    default void updateData(T entity, U updateDto) {
    }
    default List<T> getAll() {
        return Collections.emptyList();
    }
}
