package br.com.devbean.domain.usecases;

import br.com.devbean.domain.services.TodoService;

import java.util.UUID;

public class DeleteTodoUseCase {

    private final TodoService todoService;

    public DeleteTodoUseCase(TodoService todoService) {
        this.todoService = todoService;
    }

    public void execute(final UUID pid) {
        todoService.delete(pid);
    }

}
