package com.grupo03.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbEventsRoom")
public class EventRoom extends Room {

    //Variável do limite de pessoas que podem ser cadastradas na sala
    @Column
    private int capacity;

    // Lista das pessoas cadastradas na sala
    @Transient
    List<Person> personList;


    // Métodos getters/setters
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }


    //Método para listar pessoas da sala
    public List<Person> getPersonList() {
        return personList;
    }

    //Método para definir a lista de possoas da sala
    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
