package com.grupo03.dao;

import com.grupo03.model.EventRoom;
import com.grupo03.persistence.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class EventRoomDao implements DaoPattern<EventRoom> {

    private static EntityManager em;

    @Override
    public Optional<EventRoom> getById(int id) {

        EventRoom eventRoom;

        em = EntityManagerProvider.getEntityManager();

        // Consulta a sala pelo id e aramazena o resultado:
        eventRoom = em.find(EventRoom.class, id);

        em.close();

        return Optional.ofNullable(eventRoom);
    }

    @Override
    public List<EventRoom> getAll() {

        List<EventRoom> eventRoomList;

        em = EntityManagerProvider.getEntityManager();

        // Consulta e armazena a lista de salas cadastradas:
        Query consulta = em.createNativeQuery("SELECT * FROM tbEventRoom",EventRoom.class);
        eventRoomList = consulta.getResultList();

        em.close();

        return eventRoomList;
    }

    @Override
    public void save(EventRoom eventRoom) {

        em = EntityManagerProvider.getEntityManager();

        // Salva uma nova sala banco de dados:
        em.getTransaction().begin();
        em.persist(eventRoom);
        em.getTransaction().commit();

        em.close();
    }

}
