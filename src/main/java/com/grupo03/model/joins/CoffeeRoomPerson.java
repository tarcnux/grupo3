package com.grupo03.model.joins;

import com.grupo03.model.CoffeeRoom;
import com.grupo03.model.Person;

import javax.persistence.*;

@Entity
@Table(name = "tbCoffeeRoomPerson")
public class CoffeeRoomPerson {

    // identificador da relação entre a tabela Person e CoffeeRoom
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCoffeeRoomPerson")
    private int id;

    // Id da tabela Person
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPerson")
    private Person person;

    // Id da tabela CoffeeRoom
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCoffeeRoom")
    private CoffeeRoom coffeeRoom;

    @Column(nullable = false)
    private int stage;

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

}
