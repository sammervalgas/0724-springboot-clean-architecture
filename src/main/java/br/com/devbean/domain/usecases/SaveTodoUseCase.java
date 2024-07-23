package br.com.devbean.domain.usecases;

import br.com.devbean.core.shared.UseCase;
import br.com.devbean.domain.models.Todo;
import br.com.devbean.domain.services.TodoService;

public class SaveTodoUseCase implements UseCase<Todo, Todo> {

    private final TodoService todoService;

    public SaveTodoUseCase(TodoService todoService) {
        this.todoService = todoService;
    }

    public Todo execute(Todo todo) {
        return todoService.save(todo);
    }

}
