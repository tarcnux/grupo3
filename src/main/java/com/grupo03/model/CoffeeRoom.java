package com.grupo03.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "tbCoffeeRoom")
public class CoffeeRoom extends Room{

    //variável para guardar a lista de pessoas da sala
    @Transient
    List<Person> personList;

    //Método para buscar a lista de pessoas
    public List<Person> getPersonList() {
        return personList;
    }

    //Métodos para definir a lista de pessoas
    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
