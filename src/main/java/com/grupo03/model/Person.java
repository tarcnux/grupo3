package com.grupo03.model;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "tbPerson")
public class Person {

    //Gera o id de person automáticamente
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //Gera a coluna name no banco
    @Column
    private String lastname;

    //Gera a coluna lastname no banco
    @Column
    private String name;

    @Column
    private int position;

    @Transient
    private List<EventRoom> eventRoomList;

    @Transient
    private List<CoffeeRoom> coffeeRoomList;


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


    //Método para pegar lista de salas de evento
    public List<EventRoom> getEventRoomList(List<EventRoom> eventRoomList){
        return eventRoomList;
    };

    //Método para buscar lista de salas de café

    public List<CoffeeRoom> getEventCoffeeRoomList(List<CoffeeRoom> coffeeRoomList){
        return coffeeRoomList;
    };




}
