package com.grupo03.model;

import com.grupo03.model.joins.EventRoomPerson;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbEventRoom")
@AttributeOverride(name = "id", column = @Column(name = "idEventRoom"))
public class EventRoom extends Room {

    //Variável do limite de pessoas que podem ser cadastradas na sala
    @Column(nullable = false)
    private int capacity;

    // Lista das pessoas cadastradas na sala
    @Transient
    List<Person> personList;

    // Variável de referência para tabela EventRoomHasPerson
    @Transient
    @OneToMany(mappedBy = "eventRoom")
    private Set<EventRoomPerson> eventRoomPerson = new HashSet<EventRoomPerson>();


    // Métodos getters/setters
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<EventRoomPerson> getEventRoomPerson() {
        return eventRoomPerson;
    }

    public void setEventRoomPerson(Set<EventRoomPerson> eventRoomPerson) {
        this.eventRoomPerson = eventRoomPerson;
    }


    //Método para listar pessoas da sala
    public List<Person> getPersonList() {
        return personList;
    }

    //Método para definir a lista de pessoas da sala
    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }


}
