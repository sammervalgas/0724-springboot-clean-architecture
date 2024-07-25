package br.com.devbean.domain.usecases;

import br.com.devbean.domain.models.Todo;
import br.com.devbean.domain.services.TodoService;

import java.util.Optional;
import java.util.UUID;

public class UpdateTodoUseCase {

    private final TodoService service;

    public UpdateTodoUseCase(TodoService service) {
        this.service = service;
    }

    public Optional<Todo> execute(UUID pid, Todo todo) {
        return service.update(pid, todo);
    }



}
