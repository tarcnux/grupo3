package com.grupo03.model;

import com.grupo03.model.joins.CoffeeRoomPerson;
import com.grupo03.model.joins.EventRoomPerson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Essa classe representa a entidade Pessoa (tbPerson no banco de dados)
 * que deve ser cadastradas/alocadas nas salas e espaços de café do
 * do evento através das classes EventRoomPerson e CoffeeRoomPerson.
 * @see com.grupo03.model.joins.CoffeeRoomPerson
 * @see com.grupo03.model.joins.EventRoomPerson
 *
 * {@link #getCoffeeRoomPersonList()}   Retorna a listas de cadastro das
 * pessoas nas salas.
 * {@link #getCoffeeRoomPersonList()}   Retorna a listas de cadastro das
 * pessoas nos espaços de café.
 *
 * @author  Carlos Eduardo Ribeiro
 * @author  Guilherme Peyerl Florêncio
 * @version 1.0
 */
@Entity
@Table(name = "tbPerson")
public class Person {

    /**
     * Identificador da pessoa no banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerson")
    private int id;

    /**
     * Nome da pessoa.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Sobrenome da pessoa.
     */
    @Column(nullable = false)
    private String lastname;

    /**
     * Posição que a pessoa será alocada nas salas. Serve como
     * base para fazer a alocação das pessoas cadastradas nas salas.
     */
    @Column
    private int seat;

    /**
     * Define a associação com a classe EventRoomPeson que representa o
     * relacionamento muitos-para-muitos entre as classes EventRoom e Person.
     */
    @OneToMany(mappedBy = "person")
    private List<EventRoomPerson> eventRoomPersonList = new ArrayList<>();

    /**
     * Define a associação com a classe CoffeeRoomPeson que representa o
     * relacionamento muitos-para-muitos entre as classes CoffeeRoom e
     * Person.
     */
    @OneToMany(mappedBy = "person")
    private List<CoffeeRoomPerson> coffeeRoomPersonList = new ArrayList<>();

    /**
     * Construtor sem argumento.
     */
    public Person() {
    }

    /**
     * Construtor da classe especificando o id.
     * @param id    o identificador da pessoa no bannco de dados (chave primária)
     */
    public Person(int id) {
        this.id = id;
    }

    /**
     *  Construtor da classe especificando o nome e sobrenome.
     * @param name      o nome da pessoa
     * @param lastname  o sobrenome da pessoa
     */
    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    /**
     * Construtor da classe especificando nome, sobrenome e a posição.
     * @param name      o nome da pessoa
     * @param lastname  o sobrenome da pessoa
     * @param seat      o posição que a pessoa será alocada nas salas (cadeira/ascento)
     */
    public Person(String name, String lastname, int seat) {
        this.name = name;
        this.lastname = lastname;
        this.seat = seat;
    }


    // Getters | Setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    /**
     * Busca e retorna a lista com os cadastros que a pessoa
     * possui nas salas.
     * @return  um List da classe EventRoomPerson
     */
    public List<EventRoomPerson> getEventRoomPersonList() {
        return eventRoomPersonList;
    }

    public void setEventRoomPersonList(List<EventRoomPerson> eventRoomPersonList) {
        this.eventRoomPersonList = eventRoomPersonList;
    }

    /**
     * Busca e retorna a lista dos cadastros que a pessoa possui
     * nos espaços de café.
     * @return  um List da classe CoffeeRoomPerson
     */
    public List<CoffeeRoomPerson> getCoffeeRoomPersonList() {
        return coffeeRoomPersonList;
    }

    public void setCoffeeRoomPersonList(List<CoffeeRoomPerson> coffeeRoomPersonList) {
        this.coffeeRoomPersonList = coffeeRoomPersonList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", seat=" + seat +
                '}';
    }

}
