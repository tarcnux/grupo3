package com.grupo03.model;

import com.grupo03.model.joins.EventRoomPerson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representa a entidade Sala (tbEventRoom no banco de dados). Ela
 * erda da classe Room que contém os atributos essenciais para um
 * espaço de café ou sala no evento.</br>
 * A classe representa faz associação muitos-para-muitos com a
 * entidade Pessoa (tbPerson no banco de dados), essa associação
 * é definida pela classe EventRoomPerson que representa uma tabela
 * join (tbEventRoomPerson no banco de dados).
 * @see com.grupo03.model.Room
 * @see com.grupo03.model.Person
 * @see com.grupo03.model.joins.EventRoomPerson
 *
 * {@link #getPersonList(int)} Retorna a lista de pessoas cadastradas
 * na sala pela etapa do evento
 * {@link #getEventRoomPersonList()}   Retorna a listas de cadastro das
 * pessoas nas salas
 *
 * @author  Carlos Eduardo Ribeiro
 * @author  Guilherme Peyerl Florêncio
 * @version 1.0
 */
@Entity
@Table(name = "tbEventRoom")
@AttributeOverride(name = "id", column = @Column(name = "idEventRoom"))
public class EventRoom extends Room {

    /**
     * Representa a lotação máxima que a classe terá no evento.
     */
    @Column(nullable = false)
    private int capacity;

    /**
     * Define a associação com a classe EventRoomPeson que representa o
     * relacionamento muitos-para-muitos entre as classes EventRoom e Person.
     */
    @OneToMany(mappedBy = "eventRoom")
    private List<EventRoomPerson> eventRoomPersonList = new ArrayList<>();

    /**
     * Construtor sem argumento.
     */
    public EventRoom() {
    }

    /**
     * Construtor da classe especificando o id.
     * @param id    O identificador da sala no bannco de dados (chave primária)
     */
    public EventRoom(int id) {
        this.id = id;
    }

    /**
     * Construtor da sala especificando nome e lotação máxima.
     * @param name      O nome da sala
     * @param capacity  A Lotação máxima da sala
     */
    public EventRoom(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * Retorna uma lista dos relacionamentos que a
     * sala possui com a entidade pessoa.
     * @return  Um List da classe EventRoomPerson
     */
    public List<EventRoomPerson> getEventRoomPersonList() {
        return eventRoomPersonList;
    }

    public void setEventRoomPersonList(List<EventRoomPerson> eventRoomPersonList) {
        this.eventRoomPersonList = eventRoomPersonList;
    }

    /**
     * Retorna uma lista de pessoas cadastradas na sala
     * através da associação criada pela classe EventRoomPerson
     * e pela etapa.
     * @param stage A etapa do evento.
     * @return      Um List da classe Person
     */
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
