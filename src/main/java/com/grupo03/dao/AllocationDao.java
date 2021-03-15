package com.grupo03.dao;


import com.grupo03.model.CoffeeRoom;
import com.grupo03.model.EventRoom;
import com.grupo03.model.Person;
import com.grupo03.model.joins.CoffeeRoomPerson;
import com.grupo03.model.joins.EventRoomPerson;
import com.grupo03.persistence.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

/**
 *  Classe responsável por fazer a alocação de uma lista de pessoas
 *  em salas de eventos e em ambientes de café seguindo a Regra de Negócio
 *  "A diferença de pessoas em cada sala deverá ser de no máximo 1 pessoa.
 *  Para estimular a troca de conhecimentos, metade das pessoas precisam trocar
 *  de sala entre as duas etapas do treinamento."
 *  @see com.grupo03.model.CoffeeRoom
 *  @see com.grupo03.model.EventRoom;
 *  @see com.grupo03.model.Person;
 *  @see com.grupo03.model.joins.CoffeeRoomPerson;
 *  @see com.grupo03.model.joins.EventRoomPerson;
 *  @see com.grupo03.persistence.EntityManagerProvider;
 *  @see javax.persistence.EntityManager;
 *
 * {@link #alocar()} Percorre uma lista de pessoas e aloca nas salas por etapas
 *
 * @author Carlos Eduardo Ribeiro (carloseduribeiro)
 * @author Guilherme Peyerl Florêncio (GuilhermePeyflo)
 * @author Tarcísio Nunes (tarcnux)
 */
public class AllocationDao {
        List<Person> listPerson;
//        List<CoffeeRoom> listCoffeeRoom;
//        List<EventRoom> listEventRoom;

        EntityManager em = EntityManagerProvider.getEntityManager();

        Integer seat;

        Integer currentEventRoomId;
        Integer lastEventRoomId;

        Integer currentCoffeeRoomId;
        Integer lastCoffeeRoomId;

        EventRoomPerson personEventRoom;
        CoffeeRoomPerson personCoffeeRoom;

        CoffeeRoomDao cr = new CoffeeRoomDao();
        EventRoomDao er = new EventRoomDao();


    /**
     * Construtor do Objeto AlocationDao
     * Inicializa uma lista de pessoas
     * Inicializa lastCoffeeRoomId com o tamanho da lista CoffeeRoom
     * Inicializa lastEventRoomId com o tamanho da lista EventRoom
     * Incializa seat = 1, definindo o primeiro assento de cada sala
     * @param listPerson
     */
    public AllocationDao(List<Person> listPerson) {
            this.listPerson = listPerson;
            lastCoffeeRoomId = cr.getAll().size();
            lastEventRoomId = er.getAll().size();
            seat = 1;

            currentEventRoomId = 1;
            currentCoffeeRoomId = 1;
        }

    /**
     * Método que faz a alocação da lista de pessoas nas salas, através
     * do relacionamento entre as entidades: Person, Coffeeroom e EventRoom
     * CoffeeRoomPerson - join entre Person e CoffeeRoom
     * EventRoomPerson - join entre Person e EventRoom
     */
    public void alocar() {

            em.getTransaction().begin();
        listPerson.forEach(person -> {
            /*
                Cada sala tem um assento com o mesmo número de todas as outras salas, ou seja:
                sala 1 assento 1, sala 2 assento 1, sala 3 assento 1
                sala 1 assento 2, sala 2 assento 2, sala 3 assento 2
                sala 1 assento N, sala 2 assento N, sala 3 assento N
            */
            person.setSeat(seat);

            if(seat % 2 != 0) { //ÍMPAR
                //Pessoa no assento ímpar não troca de sala na 2ª etapa

                //Alocação da pessoa na mesma sala de café etapa 1 e 2
                CoffeeRoomDao coffeeRoomDao = new CoffeeRoomDao();
                Optional<CoffeeRoom> objectCR = coffeeRoomDao.getById(currentCoffeeRoomId);
               if (objectCR.isPresent()){
                   CoffeeRoom coffeeRoom = objectCR.get();
                   personCoffeeRoom = new CoffeeRoomPerson(person, coffeeRoom, 1);
                   personCoffeeRoom = em.merge(personCoffeeRoom);
                   em.persist(personCoffeeRoom);

                   personCoffeeRoom = new CoffeeRoomPerson(person, coffeeRoom, 2);
                   personCoffeeRoom = em.merge(personCoffeeRoom);
                   em.persist(personCoffeeRoom);
               }

                //Alocação da pessoa na mesma sala de Evento etapa 1 e 2
                EventRoomDao eventRoomDao = new EventRoomDao();
                Optional<EventRoom> objectER = eventRoomDao.getById(currentEventRoomId);
                if (objectER.isPresent()){
                    EventRoom eventRoom = objectER.get();
                    personEventRoom = new EventRoomPerson(person, eventRoom, 1);
                    personEventRoom = em.merge(personEventRoom);
                    em.persist(personEventRoom);

                    personEventRoom = new EventRoomPerson(person, eventRoom, 2);
                    personEventRoom = em.merge(personEventRoom);
                    em.persist(personEventRoom);
                }

            } else { //PAR
                /*
                    Pessoa no assento par troca de sala na 2ª etapa
                    Etapa 1 - Sala atual Café atual
                    Etapa 2 - Próxima sala  próximo Café
                 */

                //Alocação da pessoa na sala de café atual etapa 1
                CoffeeRoomDao coffeeRoomDao = new CoffeeRoomDao();
                Optional<CoffeeRoom> objectCR = coffeeRoomDao.getById(currentCoffeeRoomId);
                if (objectCR.isPresent()){
                    CoffeeRoom coffeeRoom = objectCR.get();
                    personCoffeeRoom = new CoffeeRoomPerson(person, coffeeRoom, 1);
                    personCoffeeRoom = em.merge(personCoffeeRoom);
                    em.persist(personCoffeeRoom);
                }

                //Alocação da pessoa na sala de evento atual etapa 1
                EventRoomDao eventRoomDao = new EventRoomDao();
                Optional<EventRoom> objectER = eventRoomDao.getById(currentEventRoomId);
                if (objectER.isPresent()){
                    EventRoom eventRoom = objectER.get();
                    personEventRoom = new EventRoomPerson(person, eventRoom, 1);
                    personEventRoom =  em.merge(personEventRoom);
                    em.persist(personEventRoom);
                }

                /*
                    Preparar para pegar a próxima sala
                    Como a próxima sala pode não existir, se estouraR a lista
                    temos que validar isso para saber qual é a próxima sala, mas
                    sem incrementar, pois não estamos rotacionando sala ainda
                 */

                Optional<EventRoom> objectERStage2;
                if(currentEventRoomId == lastEventRoomId){
                    /*
                        Aqui acontece o estouro de Lista se incrementar
                        Pegando a sala 1 de evento da lista para Stage 2
                     */
                    objectERStage2 = eventRoomDao.getById(1);
                    if (objectERStage2.isPresent()){
                        EventRoom eventRoom = objectERStage2.get();
                        personEventRoom = new EventRoomPerson(person, eventRoom, 2);
                        personEventRoom = em.merge(personEventRoom);
                        em.persist(personEventRoom);
                    }


                } else {
                    /*
                        Como não é a última sala, a próxima sala é +1
                        Mas sem incrementar, não é hora disso
                        Pegando a PRÓXIMA sala de evento da lista para Stage 2
                     */
                    objectERStage2 = eventRoomDao.getById(currentEventRoomId + 1);
                    if (objectERStage2.isPresent()){
                        EventRoom eventRoom = objectERStage2.get();
                        personEventRoom = new EventRoomPerson(person, eventRoom, 2);
                        personEventRoom = em.merge(personEventRoom);
                        em.persist(personEventRoom);
                    }
                }

                Optional<CoffeeRoom> objectCRStage2;
                if(currentCoffeeRoomId == lastCoffeeRoomId) {
                    /*
                        Aqui acontece o estouro de Lista se incrementar
                        Pegando o ambiente de café 1 da lista para Stage 2
                     */
                    objectCRStage2 = coffeeRoomDao.getById(1);
                    if (objectCRStage2.isPresent()){
                        CoffeeRoom coffeeRoom = objectCRStage2.get();
                        personCoffeeRoom = new CoffeeRoomPerson(person, coffeeRoom, 2);
                        personCoffeeRoom = em.merge(personCoffeeRoom);
                        em.persist(personCoffeeRoom);
                    }

                } else {
                    /*
                        Como não é o último ambiente, o próximo ambiente é +1
                        Mas sem incrementar, não é hora disso
                        Pegando o PRÓXIMO ambiente de café da lista para Stage 2
                     */
                    objectCRStage2 = coffeeRoomDao.getById(currentCoffeeRoomId + 1);
                    if (objectCRStage2.isPresent()){
                        CoffeeRoom coffeeRoom = objectCRStage2.get();
                        personCoffeeRoom = new CoffeeRoomPerson(person, coffeeRoom, 2);
                        personCoffeeRoom = em.merge(personCoffeeRoom);
                        em.persist(personCoffeeRoom);
                    }
                }

            }

            //Incrementando para a próxima sala sem mudar o assento (seat) ainda
            currentEventRoomId++;
            currentCoffeeRoomId++;

            /*
                Vamos verificar se passou da última sala e então mudamos o assento
                Imagina que temos duas salas, teremos então:
                assento 1 na sala 1 e assento 1 na sala 2
                só então passa para o assento 2
                 assento 2 na sala 1 e assento 2 na sala 2
             */
            seat = (currentEventRoomId > lastEventRoomId) ? ++ seat : seat;

            /*
                Reset índice da Sala de Eventos
                Se chegou na última sala, devemos dar um reset no índice para voltar à primeira sala
             */

            currentEventRoomId = (currentEventRoomId > lastEventRoomId) ?
                    1 : currentEventRoomId;

            /*
                Reset índice do Ambiente de Café
                Se chegou no último ambiente, devemos dar um reset no índice para voltar ao primeiro ambiente
             */

            currentCoffeeRoomId = (currentCoffeeRoomId > lastCoffeeRoomId) ?
                    1 : currentCoffeeRoomId;


        });
        em.getTransaction().commit();
        em.close();
    }

}
