package com.grupo03.tests;

import com.grupo03.dao.EventRoomDao;
import com.grupo03.model.EventRoom;
import junit.framework.AssertionFailedError;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class EventRoomDaoTest {

    private final EventRoomDao dao = new EventRoomDao();

    @Before
    public void setUp(){}

    @After
    public void tearDown(){}

    @Test
    public void testEventRoomDaoGetById(){

        int id = 1;
        Optional<EventRoom> eventRoom = dao.getById(id);

        try {

            Assert.assertTrue(eventRoom.isPresent());
            System.out.print("[PASSED]: Método getById - EventRoomDao passou.");

        } catch (AssertionError e) {
            Assert.fail();
            Assert.fail("[FAILED]: Método getById - EventRoomDao falhou. Falha: " + e.getMessage());
        }
    }

    @Test
    public void testEventRoomDaoGetAll(){

        List<EventRoom> eventRoomList = dao.getAll();

        try {

            Assert.assertFalse(eventRoomList.isEmpty());
            System.out.print("[PASSED]: Método getAll - EventRoomDao passou.");

        } catch (AssertionError e) {
            Assert.fail();
            Assert.fail("[FAILED]: Método getAll - EventRoomDao falhou. Falha: " + e.getMessage());
        }
    }

    @Test
    public void testEventRoomDaoSave(){

        try {

            // Do something

        } catch (AssertionFailedError e) {

            Assert.fail();
            Assert.fail("[FAILED]: Método save - EventRoomDao falhou. Falha: " + e.getMessage());

        }
    }

    @Test
    public void testEventRoomDaoGetUserRoom(){


    }
}
