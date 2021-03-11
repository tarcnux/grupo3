package com.grupo03.persistence;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {

    // Nome da Persistence Unit:
    private static final String PU_NAME = "ProwayEM-PU";

    /*
     * * EntityManagerFactory deve ser estático:
     *
     * Na especificação diz que ele é multi-thread Custo 'muito alto' para você
     * instanciá-lo o tempo todo
     */

    // Instancia a factory para poder criar o EntityManager da UP:
    private static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PU_NAME);

    /*
     * * EntityManager:
     *
     * Mapeia e gerencia um conjunto de classes a um banco de dados; Este conjunto
     * de classes é chamado de PERSISTENCE UNIT.
     *
     * * EntityManager não é thread safe:
     *
     * A especificação diz que ele não deve ser reutilizado; O ideal é, abrir um
     * quando o request chegar e depois finalizar; Se deixá-lo aberto, os recursos
     * com o banco de dados podem se esgotar.
     */

    // Retorna o EntityManager da PU:
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

}
