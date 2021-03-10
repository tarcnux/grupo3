package com.grupo03.model.joins;

import com.grupo03.model.EventRoom;
import com.grupo03.model.Person;

import javax.persistence.*;

@Entity
@Table(name = "tbEventRoomPerson")
public class EventRoomPerson {

    // identificador da relação entre a tabela Person e EventRoom
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEventRoomPerson")
    private int id;

    // Id da tabela Person
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPerson")
    private Person person;

    // Id da tabela EventRoom
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEventRoom")
    private EventRoom eventRoom;

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

    public EventRoom getEventRoom() {
        return eventRoom;
    }

    public void setEventRoom(EventRoom eventRoom) {
        this.eventRoom = eventRoom;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

}
