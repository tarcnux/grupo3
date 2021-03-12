package com.grupo03.model;

import javax.persistence.*;
import java.util.Objects;

@MappedSuperclass
public abstract class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @Column(nullable = false)
    protected String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return getId() == room.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

}
