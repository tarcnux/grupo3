package com.grupo03.model;

import com.grupo03.model.joins.CoffeeRoomPerson;
import com.grupo03.model.joins.EventRoomPerson;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "tbPerson")
public class Person {

    //Gera o id de person automaticamente
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerson")
    private int id;

    //Gera a coluna name no banco
    @Column(nullable = false)
    private String name;

    //Gera a coluna lastname no banco
    @Column(nullable = false)
    private String lastname;

    @Column
    private int seat;

    //Variável de referência para a tabela EventRoomPerson
    @Transient
    @OneToMany(mappedBy = "person")
    private Set<EventRoomPerson> eventHasPerson = new HashSet<EventRoomPerson>();

    @Transient
    @OneToMany(mappedBy = "person")
    private Set<CoffeeRoomPerson> coffeeRoomPerson = new HashSet<CoffeeRoomPerson>();

    @Transient
    private List<EventRoom> eventRoomList;

    @Transient
    private List<CoffeeRoom> coffeeRoomList;

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

    //Métodos padrões get/set
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

    public Set<EventRoomPerson> getEventHasPerson() {
        return eventHasPerson;
    }

    public void setEventHasPerson(Set<EventRoomPerson> eventHasPerson) {
        this.eventHasPerson = eventHasPerson;
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


    //Método para pegar lista de salas de evento
    public List<EventRoom> getEventRoomList(List<EventRoom> eventRoomList){
        return eventRoomList;
    };

    //Método para buscar lista de salas de café

    public List<CoffeeRoom> getEventCoffeeRoomList(List<CoffeeRoom> coffeeRoomList){
        return coffeeRoomList;
    };




}
