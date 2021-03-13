package com.grupo03.model;

import com.grupo03.model.joins.CoffeeRoomPerson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tbCoffeeRoom")
@AttributeOverride(name = "id", column = @Column(name = "idCoffeeRoom"))
public class CoffeeRoom extends Room {

    // Associação com a entidade tbCoffeeRoomPerson:
    @OneToMany(mappedBy = "coffeeRoom")
    private List<CoffeeRoomPerson> coffeeRoomPersonList = new ArrayList<>();


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

    public List<CoffeeRoomPerson> getCoffeeRoomPersonList() {
        return coffeeRoomPersonList;
    }

    public void setCoffeeRoomPersonList(List<CoffeeRoomPerson> coffeeRoomPersonList) {
        this.coffeeRoomPersonList = coffeeRoomPersonList;
    }

    public List<Person> getPersonList(int stage) {

        // Busca a lista de pessoas associadas a pessoa pelo stage:
        List<CoffeeRoomPerson> stageCoffeeRoomPersonList =
                this.getCoffeeRoomPersonList().stream().
                        filter(e -> e.getStage() == stage).
                        collect(Collectors.toList());

        var result = new ArrayList<Person>();
        for (CoffeeRoomPerson coffeeRoomPerson : stageCoffeeRoomPersonList) {
            result.add(coffeeRoomPerson.getPerson());
        }

        return result;
    }

    @Override
    public String toString() {
        return "CoffeeRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
