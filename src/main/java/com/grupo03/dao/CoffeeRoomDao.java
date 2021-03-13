package com.grupo03.dao;

import com.grupo03.model.CoffeeRoom;
import com.grupo03.persistence.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

public class CoffeeRoomDao implements DaoPattern<CoffeeRoom> {

    private static EntityManager em;

    @Override
    public Optional<CoffeeRoom> getById(int id) {

        CoffeeRoom coffeeRoom;

        em = EntityManagerProvider.getEntityManager();

        // Consulta a sala de café pelo id e armazena o resultado:
        coffeeRoom = em.find(CoffeeRoom.class, id);

        em.close();

        return Optional.ofNullable(coffeeRoom);
    }

    @Override
    public List<CoffeeRoom> getAll() {

        List<CoffeeRoom> coffeeRoomList;

        em = EntityManagerProvider.getEntityManager();

        // Consulta e armazena a lista de salas de café cadastradas:
        Query consulta = em.createNativeQuery("SELECT * FROM tbCoffeeRoom",CoffeeRoom.class);
        coffeeRoomList = consulta.getResultList();

        em.close();

        return coffeeRoomList;
    }

    //Cadastra uma nova sala de café no banco de dados
    @Override
    public CoffeeRoom save(CoffeeRoom coffeeRoom) {

        CoffeeRoom result;
        em = EntityManagerProvider.getEntityManager();

        // Salva uma nova sala de café no banco de dados:
        em.getTransaction().begin();
        result = em.merge(coffeeRoom);
        em.getTransaction().commit();

        em.close();

        return result;
    }

}
