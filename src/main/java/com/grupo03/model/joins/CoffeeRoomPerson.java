package com.grupo03.model.joins;

import com.grupo03.model.CoffeeRoom;
import com.grupo03.model.Person;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tbCoffeeRoomPerson")
public class CoffeeRoomPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCoffeeRoomPerson")
    private int id;

    // Faz a associação com a entidade tbPerson:
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPerson")
    private Person person;

    // Faz a associação com a entidade tbCoffeeRoom:
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCoffeeRoom")
    private CoffeeRoom coffeeRoom;

    @Column(nullable = false)
    private int stage;


    // Construtores:

    public CoffeeRoomPerson() {
    }

    public CoffeeRoomPerson(int id) {
        this.id = id;
    }

    public CoffeeRoomPerson(Person person, CoffeeRoom coffeeRoom, int stage) {
        this.person = person;
        this.coffeeRoom = coffeeRoom;
        this.stage = stage;
    }


    // Getters | Setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public CoffeeRoom getCoffeeRoom() {
        return coffeeRoom;
    }

    public void setCoffeeRoom(CoffeeRoom coffeeRoom) {
        this.coffeeRoom = coffeeRoom;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoffeeRoomPerson that = (CoffeeRoomPerson) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "CoffeeRoomPerson{" +
                "id=" + id +
                ", " + person.toString() +
                ", " + coffeeRoom.toString() +
                ", stage=" + stage +
                '}';
    }

}
