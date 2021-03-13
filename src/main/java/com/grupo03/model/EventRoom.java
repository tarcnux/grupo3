package com.grupo03.model;

import com.grupo03.model.joins.EventRoomPerson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "tbEventRoom")
@AttributeOverride(name = "id", column = @Column(name = "idEventRoom"))
public class EventRoom extends Room {

    // Limite de pessoas:
    @Column(nullable = false)
    private int capacity;

    // Associação com a entidade tbEventRoomPerson:
    @OneToMany(mappedBy = "eventRoom")
    private List<EventRoomPerson> eventRoomPersonList = new ArrayList<>();


    // Construtores:

    public EventRoom() {
    }

    public EventRoom(int id) {
        this.id = id;
    }

    public EventRoom(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }


    // Getters|setters:

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<EventRoomPerson> getEventRoomPersonList() {
        return eventRoomPersonList;
    }

    public void setEventRoomPersonList(List<EventRoomPerson> eventRoomPersonList) {
        this.eventRoomPersonList = eventRoomPersonList;
    }

    public List<Person> getPersonList(int stage) {

        // Busca a lista de pessoas associadas a pessoa pelo stage:
        List<EventRoomPerson> stageEventRoomPersonList =
                this.getEventRoomPersonList().stream().
                        filter(e -> e.getStage() == stage).
                        collect(Collectors.toList());

        var result = new ArrayList<Person>();
        for (EventRoomPerson eventRoomPerson : stageEventRoomPersonList) {
            result.add(eventRoomPerson.getPerson());
        }

        return result;
    }

    @Override
    public String toString() {
        return "EventRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }

}
