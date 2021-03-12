package com.grupo03.model.joins;

import com.grupo03.model.EventRoom;
import com.grupo03.model.Person;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tbEventRoomPerson")
public class EventRoomPerson {

    // identificador da relação entre a tabela Person e EventRoom
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEventRoomPerson")
    private int id;

    // Faz a associação com a entidade tbPerson:
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPerson")
    private Person person;

    // Faz a associação com a entidade tbEventRoom:
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idEventRoom")
    private EventRoom eventRoom;

    @Column(nullable = false)
    private int stage;


    // Construtores:

    public EventRoomPerson() {
    }

    public EventRoomPerson(int id) {
        this.id = id;
    }

    public EventRoomPerson(Person person, EventRoom eventRoom, int stage) {
        this.person = person;
        this.eventRoom = eventRoom;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventRoomPerson that = (EventRoomPerson) o;
        return getId() == that.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "EventRoomPerson{" +
                "id=" + id +
                ", " + person.toString() +
                ", " + eventRoom.toString() +
                ", stage=" + stage +
                '}';
    }

}
