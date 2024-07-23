package br.com.devbean.domain.services;

import br.com.devbean.domain.models.Todo;

import java.util.List;

/**
 * Inteface sera implementada dentro do TodoServiceImpl dentro do pacote data.services
 *
 */
public interface TodoService {

    List<Todo> listTodos();

}
