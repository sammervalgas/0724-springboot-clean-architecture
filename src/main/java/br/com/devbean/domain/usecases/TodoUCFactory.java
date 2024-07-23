package br.com.devbean.domain.usecases;

import br.com.devbean.domain.services.TodoService;

public class TodoUCFactory {

    private final ListTodoUseCase listTodoUseCase;

    public TodoUCFactory(TodoService todoService) {
        this.listTodoUseCase = new ListTodoUseCase(todoService);
    }

    public ListTodoUseCase listTodos() {
        return listTodoUseCase;
    }
}
