package com.grupo03.dao;

import com.grupo03.model.CoffeeRoom;
import com.grupo03.model.EventRoom;
import com.grupo03.model.Person;
import com.grupo03.model.joins.CoffeeRoomPerson;
import com.grupo03.model.joins.EventRoomPerson;
import com.grupo03.persistence.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.util.List;

/**
 *  Classe responsável por fazer a alocação de uma lista de pessoas em salas de
 *  eventos e em ambientes de café seguindo a Regra de Negócio:
 *  </br>
 *  "A diferença de pessoas em cada sala deverá ser de no máximo 1 pessoa. Para
 *  estimular a troca de conhecimentos, metade das pessoas precisam trocar de sala
 *  entre as duas etapas do treinamento."
 *  @see com.grupo03.model.CoffeeRoom
 *  @see com.grupo03.model.EventRoom;
 *  @see com.grupo03.model.Person;
 *  @see com.grupo03.model.joins.CoffeeRoomPerson;
 *  @see com.grupo03.model.joins.EventRoomPerson;
 *  @see com.grupo03.persistence.EntityManagerProvider;
 *  @see javax.persistence.EntityManager;
 *
 * {@link #allocate()} Percorre a lista de pessoas e aloca-as nas salas por etapas
 *
 * @author Carlos Eduardo Ribeiro (carloseduribeiro)
 * @author Guilherme Peyerl Florêncio (GuilhermePeyflo)
 * @author Tarcísio Nunes (tarcnux)
 * @version 2.0
 */
public class AllocationDao {

    private static final EntityManager em = EntityManagerProvider.getEntityManager();

    /**
     * Consulta a lista de pessoas cadastradas no banco de dados e faz distribuição
     * delas nas salas de evento e ambiente de café.
     */
    public static void allocate() {

        // Daos:
        var personDao = new PersonDao();
        var eventRoomDao = new EventRoomDao();
        var coffeeRoomDao = new CoffeeRoomDao();

        // Define o assento da primeira pessoa da lista:
        // Armazena o lugar/assento da pessoa na sala:
        int seat = 1;

        // Busca e armazena a lista de pessoas cadastradas no banco:
        List<Person> personList = personDao.getAll();

        // Busca e armazena a lista de salas/espaços de café cadastradas no banco:
        List<EventRoom> eventRoomList = eventRoomDao.getAll();
        List<CoffeeRoom> coffeeRoomList = coffeeRoomDao.getAll();

        // Armazena o índice da última sala/espaço de café cadastrada:
        int lastEventRoomIndex = eventRoomList.size() - 1;
        int coffeeEventRoomIndex = coffeeRoomList.size() - 1;

        // Armazena o índice das salas/espaçoa de café:
        int eventRoomIndex = 0;
        int coffeeRoomIndex = 0;

        // Iniciar a trasação com o banco para realizar as operações:
        em.getTransaction().begin();

        /*
            Percorre a lista de pessoas para alocá-las nas salas:
         */
        for (Person person : personList) {

            person.setSeat(seat);   // Atribui o lugar à pessoa.

            // As pessoas que serão alocadas nos assentos ímpares não mudarão de sala na mudança de etapas:
            if (seat % 2 == 1) {
                // Instancia a sala atual na lista pelo índice para fazer a associação:
                var eventRoom = eventRoomList.get(eventRoomIndex);

                // Faz a associação da sala:
                EventRoomPerson eventRoomPerson;
                eventRoomPerson = new EventRoomPerson(person, eventRoom, 1);
                em.merge(eventRoomPerson);
                eventRoomPerson = new EventRoomPerson(person, eventRoom, 2);
                em.merge(eventRoomPerson);

                // Instancia o espaço de café atual na lista pelo índice para fazer a associação:
                var coffeeRoom = coffeeRoomList.get(coffeeRoomIndex);

                // Faz a associação do espaço de fafé:
                CoffeeRoomPerson coffeeRoomPerson;
                coffeeRoomPerson = new CoffeeRoomPerson(person, coffeeRoom, 1);
                em.merge(coffeeRoomPerson);
                coffeeRoomPerson = new CoffeeRoomPerson(person, coffeeRoom, 2);
                em.merge(coffeeRoomPerson);

                /*
                    Verifica se alcançou o índice máximo da lista de espaço de café ou salas.
                    Se alcançar o índice é resetado e o assento incrementado.
                 */
                eventRoomIndex = eventRoomIndex < lastEventRoomIndex ? ++eventRoomIndex : 0;
                coffeeRoomIndex = coffeeRoomIndex < coffeeEventRoomIndex ? ++coffeeRoomIndex : 0;

            } else {    // Pessoas no assento par troca de sala na segunda etapa:

                // Armazena uma insância das classes para fazer a associação:
                EventRoomPerson eventRoomPerson;

                // Instancia a sala atual na lista pelo índice para fazer a associação:
                var eventRoom = eventRoomList.get(eventRoomIndex);

                // Aloca a pessoa na sala na primeira etapa:
                eventRoomPerson = new EventRoomPerson(person, eventRoom, 1);
                em.merge(eventRoomPerson);

                EventRoom nextEventRoom;
                // Verifica se está na ultima sala da lista:
                if (eventRoomIndex == lastEventRoomIndex) {
                    // Indice 0 pois eventRoomIndex é o último índice do laço for:
                    nextEventRoom = eventRoomList.get(0);

                    // Aloca a pessoa na segunda etapa:
                    eventRoomPerson = new EventRoomPerson(person, nextEventRoom, 2);
                    em.merge(eventRoomPerson);

                } else {
                    // Instancia a pŕoxima sala:
                    nextEventRoom = eventRoomList.get(eventRoomIndex + 1);

                    // Aloca a pessoa na segunda etapa:
                    eventRoomPerson = new EventRoomPerson(person, nextEventRoom, 2);
                    em.merge(eventRoomPerson);

                }

                // Armazena uma insância das classes para fazer a associação:
                CoffeeRoomPerson coffeeRoomPerson;

                // Instancia a sala atual na lista pelo índice para fazer a associação:
                var coffeeRoom = coffeeRoomList.get(coffeeRoomIndex);

                // Aloca a pessoa no espaço de café na primeira etapa:
                coffeeRoomPerson = new CoffeeRoomPerson(person, coffeeRoom, 1);
                em.merge(coffeeRoomPerson);

                CoffeeRoom nextCoffeeRoom;
                // Verifica se está na ultima sala da lista:
                if (coffeeRoomIndex == coffeeEventRoomIndex) {
                    // Indice 0 pois eventRoomIndex é o último índice do laço for:
                    nextCoffeeRoom = coffeeRoomList.get(0);

                    // Aloca a pessoa na segunda etapa:
                    coffeeRoomPerson = new CoffeeRoomPerson(person, nextCoffeeRoom, 2);
                    em.merge(coffeeRoomPerson);

                } else {
                    // Instancia a pŕoxima sala:
                    nextCoffeeRoom = coffeeRoomList.get(coffeeRoomIndex + 1);

                    // Aloca a pessoa na segunda etapa:
                    coffeeRoomPerson = new CoffeeRoomPerson(person, nextCoffeeRoom, 2);
                    em.merge(coffeeRoomPerson);

                }

                /*
                    Verifica se alcançou o índice máximo da lista de espaço de café ou salas
                    para resetar o índice:
                 */
                eventRoomIndex = eventRoomIndex < lastEventRoomIndex ? ++eventRoomIndex : 0;
                coffeeRoomIndex = coffeeRoomIndex < coffeeEventRoomIndex ? ++coffeeRoomIndex : 0;

            }
            // Seja 0, incrementa 1 para o próxima assento:
            seat = eventRoomIndex == 0 ? ++seat : seat;

        }

        // Faz o commit das operações e fecha o EntityManager:
        em.getTransaction().commit();
        em.close();

    }   // fim allocate();

}
