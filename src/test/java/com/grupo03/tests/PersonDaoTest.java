package com.grupo03.tests;

import com.grupo03.dao.PersonDao;
import com.grupo03.model.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class PersonDaoTest {


    private final PersonDao dao = new PersonDao();

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testPersonDaoGetById() {

        int id = 2;
        Optional<Person> person = dao.getById(id);

        try {

            Assert.assertTrue(person.isPresent());
            System.out.print("[PASSED]: Método getById - PersonDao passou. " +
                    "Encontrado: " + person.get().getName());

        } catch (AssertionError e) {

            Assert.fail("[FAILED]: Método getById - PersonDao falhou. Falha: " + e.getMessage());

        }
    }

    @Test
    public void testPersonDaoGetAll() {

        List<Person> personList = dao.getAll();

        try {

            Assert.assertFalse(personList.isEmpty());
            System.out.print("[PASSED]: Método getAll - PersonDao passou.");

        } catch (AssertionError e) {

            Assert.fail("[FAILED]: Método getAll - PersonDao falhou. " +
                    "Falha: " + e.getMessage());

        }
    }

    @Test
    public void testPersonDaoSave() {
        try {

            Person personToSave = new Person("Zlatan", "Ibrahimovic");
            dao.save(personToSave);
            // Do something

        } catch (AssertionError e) {
            Assert.fail("[FAILED]: Método save - PersonDao falhou. Falha: " + e.getMessage());
        }
    }
}
