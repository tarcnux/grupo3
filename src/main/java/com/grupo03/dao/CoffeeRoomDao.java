package com.grupo03.dao;

import com.grupo03.model.CoffeeRoom;
import com.grupo03.model.EventRoom;
import com.grupo03.model.Person;
import com.grupo03.persistence.EntityManagerProvider;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoffeeRoomDao implements DaoPattern<CoffeeRoom> {
    //Busca os dados da sala de café através do id inserido
    @Override
    public Optional<CoffeeRoom> getById(int id) {

        //Estabelecendo conexão com banco de dados
        EntityManager em = EntityManagerProvider.getEntityManager();

        //Execução do método jpa "find" que busca o item baseado no id que foi passado
        CoffeeRoom cr = em.find(CoffeeRoom.class, id);

        return Optional.of(cr);
    }

    @Override
    public List<CoffeeRoom> getAll() {

        //Estabelecendo conexão com banco de dados
        EntityManager em = EntityManagerProvider.getEntityManager();

        //Criação de query nativa para fazer a busca no banco de dados e retornar todas as salas de café
        Query q = em.createNativeQuery("select * from tbEventRoom",CoffeeRoom.class);

        //Retornando lista de todas as salas do banco
        return q.getResultList();
    }

    //Cadastra uma nova sala de café no banco de dados
    @Override
    public void save(CoffeeRoom coffeeRoom) {
        //Estabelecendo conexão com banco de dados
        EntityManager em = EntityManagerProvider.getEntityManager();

        //Usando os métodos do jpa "getTransaction().begin()" para permitir as inserções no banco
        // e o método "persist()" para cadastrar a nova sala de café
        //salvando o cadastro com a função getTransaction().commit()
        em.getTransaction().begin();
        em.persist(coffeeRoom);
        em.getTransaction().commit();
    }

    //O Método busca a lista de todas as pessoas cadastradas em uma determinada sala de café e em cada Etapa
    public List<List<Person>> getUserCoffeeRoom(String name) {

        //Estabelecendo conexão com banco de dados
        EntityManager em = EntityManagerProvider.getEntityManager();

        //Query que busca o id da sala digitado pelo usuário
        //Em q.setParemeter é feito a substituição do caractere "?" pelo nome da sala de café
        Query q = em.createNativeQuery("select idCoffeeRoom from tbcoffeeroom where name = ?");
        q.setParameter(1,name);

        //qStage1 busca todos os usuários cadastrados na sala de café durante a Etapa 1
        Query qStage1 = em.createNativeQuery("select distinct(p.name), p.lastname,p.seat,p.idPerson " +
                "from tbperson p, tbcoffeeroom cr, tbcoffeeroomperson crp " +
                "where crp.idPerson = p.idPerson and crp.idCoffeeRoom = ? " +
                "and crp.stage = 1 order by p.seat asc",Person.class);
        qStage1.setParameter(1,q.getSingleResult());

        //qStage2 busca todos os Usuários cadastrados na sala de café durante a Etapa 2
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
