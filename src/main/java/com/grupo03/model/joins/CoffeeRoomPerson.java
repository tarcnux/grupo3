package com.grupo03.model.joins;

import com.grupo03.model.CoffeeRoom;
import com.grupo03.model.Person;

import javax.persistence.*;
import java.util.Objects;

/**
 * Essa classe representa a tabela join (tbCoffeeRoomPerson no banco de
 * dados) que faz a associação muitos-para-muitos entre as tabelas
 * tbCoffeeRoom e tbPerson representadas pelas classes CoffeeRoom e
 * Person respectivamente.
 * @see com.grupo03.model.Person
 * @see com.grupo03.model.CoffeeRoom
 *
 * @author Carlos Eduardo Ribeiro
 * @author  Guilherme Peyerl Florêncio
 * @version 1.0
 */
@Entity
@Table(name = "tbCoffeeRoomPerson")
public class CoffeeRoomPerson {

    /**
     * Identificados da associação no banco de dados (chave primária).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCoffeeRoomPerson")
    private int id;

    /**
     * Define a associação com classe Person.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPerson")
    private Person person;

    /**
     * Define a associação com a classe CoffeeRoom.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCoffeeRoom")
    private CoffeeRoom coffeeRoom;

    /**
     * Define a qual etapa a associação pertence.
     */
    @Column(nullable = false)
    private int stage;

    /**
     * Construtor vazio.
     */
    public CoffeeRoomPerson() {
    }

    /**
     * Construtor CoffeeRoomPerson.
     * @param id    O identificador da associação no bannco de dados (chave primária)
     */
    public CoffeeRoomPerson(int id) {
        this.id = id;
    }

    /**
     * Cosntrutor CoffeeRoomPerson.
     * @param person        A pessoa que será cadastrada no espaço de café
     * @param coffeeRoom    O espaço de café em que a pessoa será cadstrada
     * @param stage         A etapa que a associação pertence
     */
    public CoffeeRoomPerson(Person person, CoffeeRoom coffeeRoom, int stage) {
        this.person = person;
        this.coffeeRoom = coffeeRoom;
        this.stage = stage;
    }


    // Getters | Setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public CoffeeRoom getCoffeeRoom() {
        return coffeeRoom;
    }

    public void setCoffeeRoom(CoffeeRoom coffeeRoom) {
        this.coffeeRoom = coffeeRoom;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeRoomPerson that = (CoffeeRoomPerson) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "CoffeeRoomPerson{" +
                "id=" + id +
                ", " + person.toString() +
                ", " + coffeeRoom.toString() +
                ", stage=" + stage +
                '}';
    }

}
