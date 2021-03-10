package com.grupo03.model;

import com.grupo03.model.joins.CoffeeRoomPerson;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tbCoffeeRoom")
public class CoffeeRoom extends Room{

    //variável para guardar a lista de pessoas da sala
    @Transient
    List<Person> personList;

    @Transient
    @OneToMany(mappedBy = "coffeeRoom")
    private Set<CoffeeRoomPerson> coffeRoomPerson = new HashSet<CoffeeRoomPerson>();

    // Métodos getters e setters
    public Set<CoffeeRoomPerson> getCoffeRoomPerson() {
        return coffeRoomPerson;
    }

    public void setCoffeRoomPerson(Set<CoffeeRoomPerson> coffeRoomPerson) {
        this.coffeRoomPerson = coffeRoomPerson;
    }

    //Método para buscar a lista de pessoas
    public List<Person> getPersonList() {
        return personList;
    }

    //Métodos para definir a lista de pessoas
    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
