package com.grupo03.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Fornece objetos da classe EntityManager para gerenciar as
 * as Entidade no banco de dados.
 * @see javax.persistence.EntityManager
 * @see javax.persistence.EntityManagerFactory
 * @see javax.persistence.Persistence;
 *
 * {@link #getEntityManager()} Retorna um objeto do tipo entity manager.
 *
 * @author Carlos Eduardo Ribeiro
 * @version 1.0
 */
public class EntityManagerProvider {

    /**
     * Nome da unidade de persistência do JPA.
     */
    private static final String PU_NAME = "ProwayEM-PU";

    /**
     * Instancia de objeto da classe EntityManagerFactory reponsável por fabrica
     * objetos da classe EntityManager.
     */
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory(PU_NAME);

    /**
     * Cria e retorna um objeto da classe EntityManager para gerenciar
     * as entidades do banco de dados.
     * @return  um objeto da classe EntityManager.
     */
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
