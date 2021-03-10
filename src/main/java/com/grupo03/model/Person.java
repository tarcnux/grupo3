package com.grupo03.model;

import javax.persistence.*;
import java.util.List;
import java.io.Serializable;

@Entity
public class Person {

    private static final long serialVersionUID = 1L;

    //Gera o id de person automáticamente
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //Gera a coluna name no banco
    @Column
    private String name;

    //Gera a coluna lastname no banco
    @Column
    private String lastname;

    @Column
    private int position;

    @ElementCollection
    private List<EventRoom> eventRoomList;

    @ElementCollection
    private List<CoffeeRoom> coffeeRoomList;


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
