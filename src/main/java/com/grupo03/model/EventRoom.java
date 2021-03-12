package com.grupo03.model;

import com.grupo03.model.joins.EventRoomPerson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbEventRoom")
@AttributeOverride(name = "id", column = @Column(name = "idEventRoom"))
public class EventRoom extends Room {

    // Limite de pessoas:
    @Transient
    @Column(nullable = false)
    private int capacity;

    // Associação com a entidade tbEventRoomPerson:
    @Transient
    @OneToMany(mappedBy = "eventRoom")
    private List<EventRoomPerson> eventRoomPersonList = new ArrayList<>();

    // Lista de pessoas cadastradas na sala:
    @Transient
    List<Person> personList;


    // Construtores:

    public EventRoom() {
    }

    public EventRoom(int id) {
        this.id = id;
    }

    public EventRoom(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }


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

    public List<EventRoomPerson> getEventRoomPersonList() {
        return eventRoomPersonList;
    }

    public void setEventRoomPersonList(List<EventRoomPerson> eventRoomPersonList) {
        this.eventRoomPersonList = eventRoomPersonList;
    }

    @Override
    public String toString() {
        return "EventRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }

}
