package com.grupo03.model.joins;

import com.grupo03.model.EventRoom;
import com.grupo03.model.Person;

import javax.persistence.*;
import java.util.Objects;

/**
 * Representa a tabela join (tbEventRoomPerson no banco de dados)
 * que faz a associação muitos-para-muitos entre as tabelas
 * tbEventRoom e tbPerson representadas pelas classes EventRoom e
 * Person respectivamente.
 * @see com.grupo03.model.Person
 * @see com.grupo03.model.EventRoom
 *
 * @author  Carlos Eduardo Ribeiro
 * @author  Guilherme Peyerl Florêncio
 * @version 1.0
 */
@Entity
@Table(name = "tbEventRoomPerson")
public class EventRoomPerson {

    /**
     * Identificador da associação no banco de dados (chave primária).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEventRoomPerson")
    private int id;

    /**
     * Define a associação com classe Person.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPerson")
    private Person person;

    /**
     * Define a associação com a classe EventRoom.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEventRoom")
    private EventRoom eventRoom;

    /**
     * Define a qual etapa a associação pertence.
     */
    @Column(nullable = false)
    private int stage;

    /**
     * Construtor sem argumento.
     */
    public EventRoomPerson() {
    }

    /**
     * Constutor da classe especificando o id.
     * @param id    O identificador da associação no bannco de dados (chave primária)
     */
    public EventRoomPerson(int id) {
        this.id = id;
    }

    /**
     * Construtor da classe espesificando a pessoa, sala e etapa.
     * @param person    A pessoa que será cadastrada na sala
     * @param eventRoom A sala em que a pessoa será cadastrada
     * @param stage     A etapa que a associação pertence
     */
    public EventRoomPerson(Person person, EventRoom eventRoom, int stage) {
        this.person = person;
        this.eventRoom = eventRoom;
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

    public EventRoom getEventRoom() {
        return eventRoom;
    }

    public void setEventRoom(EventRoom eventRoom) {
        this.eventRoom = eventRoom;
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
        EventRoomPerson that = (EventRoomPerson) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "EventRoomPerson{" +
                "id=" + id +
                ", " + person.toString() +
                ", " + eventRoom.toString() +
                ", stage=" + stage +
                '}';
    }

}
