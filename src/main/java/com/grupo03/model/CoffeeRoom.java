package com.grupo03.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.List;

@Entity
public class CoffeeRoom extends Room{

    @ElementCollection
    List<Person> personList;

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
