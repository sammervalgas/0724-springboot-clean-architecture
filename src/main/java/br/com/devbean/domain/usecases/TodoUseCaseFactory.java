package br.com.devbean.domain.usecases;

import br.com.devbean.domain.services.TodoService;

public class TodoUseCaseFactory {

    private final ListTodoUseCase listTodoUseCase;
    private final SaveTodoUseCase saveTodoUseCase;
    private final DeleteTodoUseCase deleteTodoUseCase;
    private final FindTodoUseCase findTodoUseCase;
    private final UpdateTodoUseCase updateTodoUseCase;

    public TodoUseCaseFactory(TodoService todoService) {
        this.listTodoUseCase = new ListTodoUseCase(todoService);
        this.saveTodoUseCase = new SaveTodoUseCase(todoService);
        this.deleteTodoUseCase = new DeleteTodoUseCase(todoService);
        this.findTodoUseCase = new FindTodoUseCase(todoService);
        this.updateTodoUseCase = new UpdateTodoUseCase(todoService);
    }

    public ListTodoUseCase listTodos() {
        return listTodoUseCase;
    }

    public SaveTodoUseCase saveTodo() {
        return saveTodoUseCase;
    }

    public DeleteTodoUseCase deleteTodo() {
        return deleteTodoUseCase;
    }

    public FindTodoUseCase findTodo() {
        return findTodoUseCase;
    }

    public UpdateTodoUseCase updateTodo() {
        return updateTodoUseCase;
    }
}
