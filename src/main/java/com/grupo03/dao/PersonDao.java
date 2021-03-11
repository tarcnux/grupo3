package com.grupo03.dao;

import com.grupo03.model.Person;
import com.grupo03.persistence.EntityManagerProvider;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

public class PersonDao implements DaoPattern<Person> {

    private static EntityManager em;

    @Override
    public Optional<Person> getById(int id) {
        this.em = EntityManagerProvider.getEntityManager();

        Person person = em.find(Person.class, id);

        return Optional.of(person);
    }

    @Override
    public List<Person> getAll() {
        this.em = EntityManagerProvider.getEntityManager();

        List<Person> result;
        this.em = EntityManagerProvider.getEntityManager();
        result = this.em.createNamedQuery("SELECT p FROM person AS p", Person.class).getResultList();
        this.em.close();

        return result;
    }

    @Override
    public void save(Person person) {
        this.em = EntityManagerProvider.getEntityManager();

        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();
    }
}
