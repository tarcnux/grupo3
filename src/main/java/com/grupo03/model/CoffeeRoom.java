package com.grupo03.model;

import com.grupo03.model.joins.CoffeeRoomPerson;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Representa a entidade Espaço de Café (tbCoffeeRoom no banco de
 * dados). Ela erda da classe Room que contém os atributos essenciais
 * para um espaço de café ou sala no evento.</br>
 * Essa classe faz associação muitos-para-muitos com a entidade pessoa
 * (tbPerson no banco de dados), essa associação é definida pela classe
 * CoffeeRoomPerson que representa uma tabela join (tbCoffeeRoomPerson
 * no banco de dados).
 * @see com.grupo03.model.Room
 * @see com.grupo03.model.Person
 * @see com.grupo03.model.joins.CoffeeRoomPerson
 *
 * {@link #getPersonList(int)} Retorna a lista de pessoas cadastradas
 * na sala pela etapa do evento
 * {@link #getCoffeeRoomPersonList()}   Retorna a listas de cadastro das
 * pessoas nos espaços de café
 *
 * @author  Carlos Eduardo Ribeiro
 * @author  Guilherme Peyerl Florêncio
 * @version 1.0
 */
@Entity
@Table(name = "tbCoffeeRoom")
@AttributeOverride(name = "id", column = @Column(name = "idCoffeeRoom"))
public class CoffeeRoom extends Room {

    /**
     * Define a associação com a classe CoffeeRoomPeson que representa o
     * relacionamento muitos-para-muitos entre as classes CoffeetRoom e
     * Person.
     */
    @OneToMany(mappedBy = "coffeeRoom")
    private List<CoffeeRoomPerson> coffeeRoomPersonList = new ArrayList<>();

    /**
     * Construtor sem argumento.
     */
    public CoffeeRoom() {
    }

    /**
     * Construtor do espaço de café especificando o id.
     * @param id    O identificador do espaço de café no bannco de dados
     *             (chave primária)
     */
    public CoffeeRoom(int id) {
        this.id = id;
    }

    /**
     * Construtor do espaço de café especificando o nome.
     * @param name  O nome do espaço de café
     */
    public CoffeeRoom(String name) {
        this.name = name;
    }

    /**
     * Retorna uma lista dos relacionamentos que o espaço de
     * café possui com a entidade pessoa.
     * @return  Um List da classe CoffeeRoomPerson
     */
    public List<CoffeeRoomPerson> getCoffeeRoomPersonList() {
        return coffeeRoomPersonList;
    }

    public void setCoffeeRoomPersonList(List<CoffeeRoomPerson> coffeeRoomPersonList) {
        this.coffeeRoomPersonList = coffeeRoomPersonList;
    }

    /**
     * Retorna uma lista de pessoas cadastradas no espaço
     * de café através da associação criada pela classe CoffeeRoomPerson
     * e pela etapa.
     * @param stage A etapa do evento.
     * @return      Um List da classe Person
     */
    public List<Person> getPersonList(int stage) {

        // Busca a lista de pessoas associadas a pessoa pelo stage:
        List<CoffeeRoomPerson> stageCoffeeRoomPersonList =
                this.getCoffeeRoomPersonList().stream().
                        filter(e -> e.getStage() == stage).
                        collect(Collectors.toList());

        var result = new ArrayList<Person>();
        for (CoffeeRoomPerson coffeeRoomPerson : stageCoffeeRoomPersonList) {
            result.add(coffeeRoomPerson.getPerson());
        }

        return result;
    }

    @Override
    public String toString() {
        return "CoffeeRoom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
