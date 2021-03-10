package com.grupo03.model;

import com.grupo03.model.joins.CoffeeRoomHasPerson;
import com.grupo03.model.joins.EventRoomHasPerson;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "tbPerson")
public class Person {

    //Gera o id de person automáticamente
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPerson")
    private int id;

    //Gera a coluna name no banco
    @Column
    private String name;

    //Gera a coluna lastname no banco
    @Column
    private String lastname;

    @Column
    private int position;

    //Variável de referência para a tabela EventRoomHasPerson
    @Transient
    @OneToMany(mappedBy = "person")
    private Set<EventRoomHasPerson> eventHasPerson = new HashSet<EventRoomHasPerson>();

    @Transient
    @OneToMany(mappedBy = "person")
    private Set<CoffeeRoomHasPerson> coffeeHasPerson = new HashSet<CoffeeRoomHasPerson>();

    @Transient
    private List<EventRoom> eventRoomList;

    @Transient
    private List<CoffeeRoom> coffeeRoomList;

    //Contrutor da classe Person
    public Person(String name, String lastname, int position){
        this.name = name;
        this.lastname = lastname;
        this.position = position;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Set<EventRoomHasPerson> getEventHasPerson() {
        return eventHasPerson;
    }

    public void setEventHasPerson(Set<EventRoomHasPerson> eventHasPerson) {
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
