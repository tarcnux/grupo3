package com.grupo03.dao;

import com.grupo03.model.EventRoom;
import com.grupo03.model.Person;
import com.grupo03.persistence.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventRoomDao implements DaoPattern<EventRoom> {

    //Busca os dados da sala através do id inserido
    @Override
    public Optional<EventRoom> getById(int id) {
        EntityManager em = EntityManagerProvider.getEntityManager();

        EventRoom ev = em.find(EventRoom.class, id);

        return Optional.of(ev);
    }

    //Buscando todas as sala cadastradas no banco
    @Override
    public List<EventRoom> getAll() {
        EntityManager em = EntityManagerProvider.getEntityManager();

        //Criação de query nativa para fazer a busca no banco de dados
        Query q = em.createNativeQuery("select * from tbEventRoom",EventRoom.class);

        //Retornando lista de todas as salas do banco
        return q.getResultList();
    }

    @Override
    public void save(EventRoom eventRoom) {
        EntityManager em = EntityManagerProvider.getEntityManager();

        em.getTransaction().begin();
        em.persist(eventRoom);
        em.getTransaction().commit();

    }

    //O Método busca a lista de todas as pessoas cadastradas em uma determinada sala e em cada Etapa
    public List<List<Person>> getUserRoom(String name) {
        EntityManager em = EntityManagerProvider.getEntityManager();

        //Query que busca o id da sala digitado pelo usuário
        //Em q.setParemeter é feito a substituição do caractere "?" pelo nome da sala
        Query q = em.createNativeQuery("select idEventRoom from tbeventroom where name = ?");
        q.setParameter(1,name);

        //qStage1 busca todos os usuários cadastrados na sala durante a Etapa 1
        Query qStage1 = em.createNativeQuery("select distinct(p.name), p.lastname,p.seat,p.idPerson " +
                "from tbperson p, tbeventroom er, tbeventroomperson erp " +
                "where erp.idPerson = p.idPerson and erp.idEventRoom = ? " +
                "and erp.stage = 1 order by p.seat asc",Person.class);
        qStage1.setParameter(1,q.getSingleResult());

        //qStage2 busca todos os Usuários cadastrados na sala durante a Etapa 2
        Query qStage2 = em.createNativeQuery("select distinct(p.name), p.lastname,p.seat,p.idPerson " +
                "from tbperson p, tbeventroom er, tbeventroomperson erp " +
                "where erp.idPerson = p.idPerson and erp.idEventRoom = ? " +
                "and erp.stage = 2 order by p.seat asc",Person.class);
        qStage2.setParameter(1,q.getSingleResult());

        //Fazendo a atribuição dos resultados das buscas para variáveis listas
        List<Person> list1 = qStage1.getResultList();
        List<Person> list2 = qStage2.getResultList();

        //Criando variável "listas" para receber as listas de usuários de cada Etapa,
        // para retornar as duas listas na mesma variável
        List<List<Person>> listas = new ArrayList<List<Person>>();
        listas.add(list1);
        listas.add(list2);

        return listas;
    }

}
