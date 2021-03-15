package com.grupo03.dao;

import java.util.List;
import java.util.Optional;

/**
 * Define uma API simples e abstrata para estabelecer um padrão
 * concreto da implementação dos métodos CRUD que manipulam
 * objetos do tipo T.
 * @param <T>   Tipo de objeto a ser manipulado.
 *
 * @author Carlos Eduardo Ribeiro
 * @author  Guilherme Peyerl Florêncio
 * @version 1.0
 */
public interface DaoPattern<T> {

    /**
     * Burca um objeto do tipo T através do identificador
     * (chave primária no banco de dados) e retornar o resultado
     * dentro de um objeto da classe Optional.
     * @param id    o identificados (chave primária)
     * @return      um objeto da classe Optional
     */
    Optional<T> getById(int id);

    /**
     * Consulta o banco de dados e retorna uma lista com todos os
     * objetos do tipo T cadastrados.
     * @return  lista de objetos T cadastrados no banco
     */
    List<T> getAll();

    /**
     * Cadastra um objeto do tipo T no banco de dados e retorna o
     * mesmo objeto salvo preenchido com todos os seus dados que
     * foram salvos no banco.
     * @param t Objeto a ser cadastrado
     * @return  Objeto cadastrado
     */
    T save(T t);

}
