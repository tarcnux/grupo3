package com.grupo03.dao;

import com.grupo03.model.Person;
import com.grupo03.persistence.EntityManagerProvider;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Esta classe possui os métodos necessários para a criação do objeto Pessoa.
 * Assim como a relação com o banco de dados para a consulta de Pessoa por Id e
 * consulta de todas as pessoas cadastradas em banco.
 * @author Natan Luis Moreira (LuisNataan);
 * @author Guilherme Peyerl Florêncio (GuilhermePeyflo)
 */
public class PersonDao implements DaoPattern<Person> {

    private static EntityManager em;

    @Override
    public Optional<Person> getById(int id) {

        Person person;

        em = EntityManagerProvider.getEntityManager();

        // Consulta a pessoa pelo id e armazena o resultado:
        person = em.find(Person.class, id);

        em.close();

        return Optional.ofNullable(person);
    }

    @Override
    public List<Person> getAll() {

        List<Person> personList;

        em = EntityManagerProvider.getEntityManager();

        // Consulta e armazena a lista de pessoas cadastradas:
        Query consulta = em.createNativeQuery("SELECT * FROM tbPerson", Person.class);
        personList = consulta.getResultList();

        em.close();

        return personList;
    }

    @Override
    public void save(Person person) {

        em = EntityManagerProvider.getEntityManager();

        // Salva uma nova pessoa no banco de dados:
        em.getTransaction().begin();
        em.persist(person);
        em.getTransaction().commit();

        em.close();
    }
}
