package com.grupo03.model;

import com.grupo03.model.joins.CoffeeRoomPerson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbCoffeeRoom")
@AttributeOverride(name = "id", column = @Column(name = "idCoffeeRoom"))
public class CoffeeRoom extends Room {

    // Associação com a entidade tbCoffeeRoomPerson:
    @Transient
    @OneToMany(mappedBy = "coffeeRoom")
    private List<CoffeeRoomPerson> coffeeRoomPersonList = new ArrayList<>();

    // Lista de pessoas cadastradas no espaço:
    @Transient
    List<Person> personList;


    // Construtores:

    public CoffeeRoom() {
    }

    public CoffeeRoom(int id) {
        this.id = id;
    }

    public CoffeeRoom(String name) {
        this.name = name;
    }


    // Getters | Setters:

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<CoffeeRoomPerson> getCoffeeRoomPersonList() {
        return coffeeRoomPersonList;
    }

    public void setCoffeeRoomPersonList(List<CoffeeRoomPerson> coffeeRoomPersonList) {
        this.coffeeRoomPersonList = coffeeRoomPersonList;
    }

    @Override
    public String toString() {
        return "CoffeeRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
