package com.grupo03.dao;

import com.grupo03.model.EventRoom;
import com.grupo03.persistence.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * É a classe responsável por conectar a model EventRoom com o banco de dados,
 * para realizar consultas e inserções no banco(tbcoffeeroom).
 * @see com.grupo03.model.EventRoom
 * @see com.grupo03.persistence.EntityManagerProvider
 * @see javax.persistence.EntityManager
 * @see javax.persistence.Query
 *
 * {@link #getById(int)} Retorna a sala de evento que possui o id passado como parâmetro
 * {@link #getAll()} Retorna uma lista de todas as salas de evento cadastradas no banco de dados
 * {@link #save(EventRoom)} Cadastra um nova sala no banco de dados
 *
 * @author Guilherme Peyerl Florêncio
 * @author Carlos Eduardo Ribeiro
 */
public class EventRoomDao implements DaoPattern<EventRoom> {

    private static EntityManager em;

    /**
     * O método retorna uma sala de evento de acordo com o id passado.
     * @param id    o identificador da sala de evento no banco de dados (chave primária)
     * @return
     */
    @Override
    public Optional<EventRoom> getById(int id) {

        EventRoom eventRoom;

        em = EntityManagerProvider.getEntityManager();

        // Consulta a sala pelo id e aramazena o resultado:
        eventRoom = em.find(EventRoom.class, id);

        em.close();

        return Optional.ofNullable(eventRoom);
    }

    /**
     * Este método retorna uma lista com todas as salas de eventos cadastradas no banco de dados
     * @return
     */
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

    /**
     * Este método salva o objeto eventRoom passado como parâmetro
     * na tabela tbeventroom do banco de dados.
     * @param eventRoom Objeto eventRoom com os atributos necessários para salvar no banco.
     * @return
     */
    @Override
    public EventRoom save(EventRoom eventRoom) {

        EventRoom result;

        em = EntityManagerProvider.getEntityManager();

        // Salva uma nova sala banco de dados:
        em.getTransaction().begin();
        result = em.merge(eventRoom);
        em.getTransaction().commit();

        em.close();

        return result;
    }

}
