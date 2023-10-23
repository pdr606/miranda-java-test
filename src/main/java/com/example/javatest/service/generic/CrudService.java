package com.example.javatest.service.generic;

import java.util.Collections;
import java.util.List;

public interface CrudService<T, C , U> {

    void save(C createDto);
    T getById(Long id);
    T update (Long id, U updateDto);
    default List<T> getAll() {
        return Collections.emptyList();
    }
    void delete(Long id);
    void updateData(T entity, U updateDto);
}
