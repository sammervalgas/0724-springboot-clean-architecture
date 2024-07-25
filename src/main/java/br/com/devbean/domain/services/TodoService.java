package br.com.devbean.domain.services;

import br.com.devbean.domain.models.Todo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Inteface sera implementada dentro do TodoServiceImpl dentro do pacote data.services
 */
public interface TodoService {

    List<Todo> listTodos();

    Todo save(Todo todo);

    Optional<Todo> update(UUID pid, Todo todo);

    Optional<Todo> findByPublicId(UUID pid);

    void delete(UUID pid);
}
