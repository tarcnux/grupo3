package com.grupo03.dao;

import com.grupo03.model.Person;

import java.util.List;
import java.util.Optional;

public class PersonDao implements DaoPattern<Person> {
    @Override
    public Optional<Person> getById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public void save(Person person) {

    }

    @Override
    public void update(Person person) {

    }

    @Override
    public void delete(Person person) {

    }
}
