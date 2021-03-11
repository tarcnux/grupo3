package com.grupo03.model;

import com.grupo03.model.joins.CoffeeRoomPerson;
import com.grupo03.model.joins.EventRoomPerson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tbPerson")
public class Person {

    //Gera o id de person automaticamente
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerson")
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column
    private int seat;

    //Variável de referência para a tabela EventRoomPerson
    @Transient
    @OneToMany(mappedBy = "person")
    private List<EventRoomPerson> eventHasPerson = new ArrayList<>();

    @Transient
    @OneToMany(mappedBy = "person")
    private List<CoffeeRoomPerson> coffeeRoomPerson = new ArrayList<>();

    @Transient
    private List<EventRoom> eventRoomList;

    @Transient
    private List<CoffeeRoom> coffeeRoomList;

    //Contrutor padrão da classe Person
    public Person(){

    }

    //Construtor da classe Person
    public Person(String name, String lastname, int seat){
        this.name = name;
        this.lastname = lastname;
        this.seat = seat;
    }

    //Construtor da classe Person sem seat
    public Person(String name, String lastname){
        this.name = name;
        this.lastname = lastname;
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

    public List<EventRoomPerson> getEventHasPerson() {
        return eventHasPerson;
    }

    public void setEventHasPerson(List<EventRoomPerson> eventHasPerson) {
        this.eventHasPerson = eventHasPerson;
    }

    public List<CoffeeRoomPerson> getCoffeeRoomPerson() {
        return coffeeRoomPerson;
    }

    public void setCoffeeRoomPerson(List<CoffeeRoomPerson> coffeeRoomPerson) {
        this.coffeeRoomPerson = coffeeRoomPerson;
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

}
