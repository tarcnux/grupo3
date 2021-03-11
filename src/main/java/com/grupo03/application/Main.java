package com.grupo03.application;


import com.grupo03.dao.EventRoomDao;
import com.grupo03.dao.PersonDao;
import com.grupo03.model.EventRoom;
import com.grupo03.model.Person;
import com.grupo03.persistence.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        EntityManager em = EntityManagerProvider.getEntityManager();

        em.getTransaction().begin();
        em.flush();
        em.getTransaction().commit();

        em.close();

        System.out.println("OK");

    }

}
