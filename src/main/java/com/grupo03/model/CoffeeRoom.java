package com.grupo03.model;

import com.grupo03.model.joins.CoffeeRoomHasPerson;

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
    private Set<CoffeeRoomHasPerson> coffeHasPerson = new HashSet<CoffeeRoomHasPerson>();

    // Métodos getters e setters
    public Set<CoffeeRoomHasPerson> getCoffeHasPerson() {
        return coffeHasPerson;
    }

    public void setCoffeHasPerson(Set<CoffeeRoomHasPerson> coffeHasPerson) {
        this.coffeHasPerson = coffeHasPerson;
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
