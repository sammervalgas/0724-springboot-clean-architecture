package br.com.devbean.domain.usecases;

import br.com.devbean.domain.models.Todo;
import br.com.devbean.domain.services.TodoService;

import java.util.Optional;
import java.util.UUID;

public class FindTodoUseCase {

    private final TodoService todoService;

    public FindTodoUseCase(TodoService todoService) {
        this.todoService = todoService;
    }

    public Optional<Todo> execute(final UUID pid) {
        return todoService.findByPublicId(pid);
    }
}
