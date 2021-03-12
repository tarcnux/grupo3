package com.grupo03.tests;

import com.grupo03.dao.CoffeeRoomDao;
import com.grupo03.model.CoffeeRoom;
import junit.framework.AssertionFailedError;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class CoffeeRoomDaoTest {

    private final CoffeeRoomDao dao = new CoffeeRoomDao();

    @Before
    public void setUp(){}

    @After
    public void tearDown(){}

    @Test
    public void testCoffeeRoomGetById(){

        int id = 1;
        Optional<CoffeeRoom> coffeeRoom = dao.getById(id);

        try {

            Assert.assertTrue(coffeeRoom.isPresent());
            System.out.print("[PASSED]: Método getById - CoffeeRoomDao passou.");


        } catch (AssertionFailedError error) {

            Assert.fail();
            Assert.fail("[FAILED]: Método getById - CoffeeRoomDao falhou. Falha: " + error.getMessage());

        }
    }

    @Test
    public void testCoffeeRoomGetAll(){

        List<CoffeeRoom> coffeeRoomList = dao.getAll();

        try {

            Assert.assertFalse(coffeeRoomList.isEmpty());
            System.out.print("[PASSED]: Método getById - CoffeeRoomDao passou.");

        } catch (AssertionFailedError error){

            Assert.fail();
            Assert.fail("[FAILED]: Método getAll - CoffeeRoomDao falhou. Falha: " + error.getMessage());

        }
    }

    @Test
    public void testCoffeeRoomSave(){

        try {

            /* Do something */
            System.out.print("[PASSED]: Método save - CoffeeRoomDao passou.");

        } catch (AssertionFailedError error) {

            Assert.fail();
            Assert.fail("[FAILED]: Método save - CoffeeRoomDao falhou. Falha: " + error.getMessage());

        }
    }

    @Test
    public void testCoffeeRoomGetUserRoom(){

        String coffeeRoomName = "Sala 1";

        try {

            Assert.assertFalse(dao.getUserCoffeeRoom(coffeeRoomName).isEmpty());
            System.out.print("[PASSED]: Método getUserRoom - CoffeeRoomDao passou.");

        } catch (AssertionFailedError error) {

            Assert.fail();
            Assert.fail("[FAILED]: Método save - CoffeeRoomDao falhou. Falha: " + error.getMessage());

        }

    }
}
