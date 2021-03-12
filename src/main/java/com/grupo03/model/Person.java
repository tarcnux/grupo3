package com.grupo03.model;

import com.grupo03.model.joins.CoffeeRoomPerson;
import com.grupo03.model.joins.EventRoomPerson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tbPerson")
public class Person {

    // Gera o id de person automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerson")
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column
    private int seat;

    // Associação com a entidade tbEventRoomPerson:
    @Transient
    @OneToMany(mappedBy = "person")
    private List<EventRoomPerson> eventRoomPersonList = new ArrayList<>();

    // Associação com a entidade tbCoffeeRoomPerson:
    @Transient
    @OneToMany(mappedBy = "person")
    private List<CoffeeRoomPerson> coffeeRoomPersonList = new ArrayList<>();

    @Transient
    private List<EventRoom> eventRoomList;

    @Transient
    private List<CoffeeRoom> coffeeRoomList;


    // Construtores:

    public Person() {
    }

    public Person(int id) {
        this.id = id;
    }

    public Person(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public Person(String name, String lastname, int seat) {
        this.name = name;
        this.lastname = lastname;
        this.seat = seat;
    }


    // Getters | Setters:

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public List<EventRoomPerson> getEventRoomPersonList() {
        return eventRoomPersonList;
    }

    public void setEventRoomPersonList(List<EventRoomPerson> eventRoomPersonList) {
        this.eventRoomPersonList = eventRoomPersonList;
    }

    public List<CoffeeRoomPerson> getCoffeeRoomPersonList() {
        return coffeeRoomPersonList;
    }

    public void setCoffeeRoomPersonList(List<CoffeeRoomPerson> coffeeRoomPersonList) {
        this.coffeeRoomPersonList = coffeeRoomPersonList;
    }

    public List<EventRoom> getEventRoomList() {
        return eventRoomList;
    }

    public void setEventRoomList(List<EventRoom> eventRoomList) {
        this.eventRoomList = eventRoomList;
    }

    public List<CoffeeRoom> getCoffeeRoomList() {
        return coffeeRoomList;
    }

    public void setCoffeeRoomList(List<CoffeeRoom> coffeeRoomList) {
        this.coffeeRoomList = coffeeRoomList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", seat=" + seat +
                '}';
    }

}
