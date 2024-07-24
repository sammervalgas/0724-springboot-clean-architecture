package br.com.devbean.domain.usecases;

import br.com.devbean.domain.services.TodoService;

public class TodoUseCaseFactory {

    private final ListTodoUseCase listTodoUseCase;
    private final SaveTodoUseCase saveTodoUseCase;

    public TodoUseCaseFactory(TodoService todoService) {
        this.listTodoUseCase = new ListTodoUseCase(todoService);
        this.saveTodoUseCase = new SaveTodoUseCase(todoService);
    }

    public ListTodoUseCase listTodos() {
        return listTodoUseCase;
    }

    public SaveTodoUseCase saveTodoUseCase() {
        return saveTodoUseCase;
    }
}