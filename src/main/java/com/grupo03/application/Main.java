package com.grupo03.application;

import com.grupo03.model.EventRoom;
import com.grupo03.model.Person;
import com.grupo03.persistence.EntityManagerProvider;

import javax.persistence.EntityManager;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        EntityManager em = EntityManagerProvider.getEntityManager();

        var persons = Arrays.asList(
                new Person("Carlos Eduardo", "Ribeiro", 1),
                new Person("Rogério", "Silva", 1),
                new Person("João Alberto", "Costa", 1),
                new Person("Lucas Eduardo", "Torquato", 1),
                new Person("Matheus", "Almeida", 1),
                new Person("Felipe", "Rodrigues", 1),
                new Person("Renan Borba", "Lemos", 1),
                new Person("José", "Bareta", 1),
                new Person("Rafael", "Yoshida", 1),
                new Person("Leonardo", "Valim", 1),
                new Person("Vitor", "Vieira", 1),
                new Person("Alvaro Rodrigo", "Pereira", 1),
                new Person("Alexandre", "Ribeiro", 1),
                new Person("Nathã", "Azevedo", 1),
                new Person("Rodrigo Otávio", "Pereira", 1),
                new Person("Valdecir", "Locatelli", 1)
        );

        var s1 = new EventRoom("Sala A", 10);
        var s2 = new EventRoom("Sala B", 15);

        var p = new Person("Guilherme", "Silva", 1);


        var teste = (Person) em.find(Person.class, 1);
        System.out.println(teste.toString());

        em.getTransaction().begin();



        em.getTransaction().commit();
        em.close();

        System.out.println("OK");

    }

}
