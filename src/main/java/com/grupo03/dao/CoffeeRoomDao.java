package com.grupo03.dao;

import com.grupo03.model.CoffeeRoom;
import com.grupo03.persistence.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/**
 * É a classe responsável por conectar a model CoffeeRoom com o banco de dados,
 * para realizar consultas e inserções no banco(tbcoffeeroom).
 *@see com.grupo03.model.CoffeeRoom
 *@see com.grupo03.persistence.EntityManagerProvider
 *@see javax.persistence.EntityManager
 *@see javax.persistence.Query
 *
 * {@link #getById(int)} Retorna a sala de café que possui o id passado como parâmetro
 * {@link #getAll()} Retorna uma lista de todas as salas de café cadastradas no banco de dados
 * {@link #save(CoffeeRoom)} Cadastra um nova sala de café no banco de dados
 *
 * @author Guilherme Peyerl Florêncio
 * @author Carlos Eduardo Ribeiro
 */
public class CoffeeRoomDao implements DaoPattern<CoffeeRoom> {

    private static EntityManager em;

    /**
     * O método retorna uma sala de café de acordo com o id passado.
     * @param id    o identificador da sala de café no banco de dados (chave primária)
     * @return
     */
    @Override
    public Optional<CoffeeRoom> getById(int id) {

        CoffeeRoom coffeeRoom;

        em = EntityManagerProvider.getEntityManager();

        // Consulta a sala de café pelo id e armazena o resultado:
        coffeeRoom = em.find(CoffeeRoom.class, id);

        em.close();

        return Optional.ofNullable(coffeeRoom);
    }

    /**
     * Este método retorna todas as salas cadastradas no banco.
     * @return
     */
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

    /**
     * Este método salva o objeto coffeeRoom passado como parâmetro
     * na tabela tbcoffeeroom do banco de dados
     * @param coffeeRoom Objeto coffeeRoom com os atributos necessários para salvar no banco
     * @return
     */
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
