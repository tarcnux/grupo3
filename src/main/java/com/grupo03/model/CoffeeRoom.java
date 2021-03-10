package com.grupo03.model;

import com.grupo03.model.joins.CoffeeRoomPerson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbCoffeeRoom")
@AttributeOverride(name = "id", column = @Column(name = "idCoffeeRoom"))
public class CoffeeRoom extends Room{

    // Lista de pessoas cadastradas no espaço:
    @Transient
    List<Person> personList;

    @Transient
    @OneToMany(mappedBy = "coffeeRoom")
    private List<CoffeeRoomPerson> coffeeRoomPerson = new ArrayList<>();


    // Getters | Setters:

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<CoffeeRoomPerson> getCoffeeRoomPerson() {
        return coffeeRoomPerson;
    }

    public void setCoffeeRoomPerson(List<CoffeeRoomPerson> coffeeRoomPerson) {
        this.coffeeRoomPerson = coffeeRoomPerson;
    }

}
