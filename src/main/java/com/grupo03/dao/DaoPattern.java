package com.grupo03.dao;

import java.util.List;
import java.util.Optional;

public interface DaoPattern<T> {

    Optional<T> getById(int id);

    List<T> getAll();

    void save(T t);

}
