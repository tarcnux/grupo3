package com.grupo03.model;

import com.grupo03.model.joins.EventRoomPerson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbEventRoom")
@AttributeOverride(name = "id", column = @Column(name = "idEventRoom"))
public class EventRoom extends Room {

    // Limite de pessoas cadastradas na sala:
    @Column(nullable = false)
    private int capacity;

    // Lista de pessoas cadastradas na sala:
    @Transient
    List<Person> personList;

    // Variável de referência para tabela EventRoomHasPerson
    @Transient
    @OneToMany(mappedBy = "eventRoom")
    private List<EventRoomPerson> eventRoomPerson = new ArrayList<>();


    // Getters|setters:

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<EventRoomPerson> getEventRoomPerson() {
        return eventRoomPerson;
    }

    public void setEventRoomPerson(List<EventRoomPerson> eventRoomPerson) {
        this.eventRoomPerson = eventRoomPerson;
    }

}
