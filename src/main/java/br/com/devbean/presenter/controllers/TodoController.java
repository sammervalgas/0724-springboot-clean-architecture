package br.com.devbean.presenter.controllers;

import br.com.devbean.domain.models.Todo;
import br.com.devbean.domain.usecases.ListTodoUseCase;
import br.com.devbean.domain.usecases.TodoUCFactory;
import br.com.devbean.presenter.dtos.TodoRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {
    

    @Autowired
    private TodoUCFactory todoUCFactory;

    @GetMapping
    public ResponseEntity<List<Todo>> list() {
        ListTodoUseCase listTodoUseCase = todoUCFactory.listTodos();

        return Optional.ofNullable(listTodoUseCase.execute())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveTodo(@RequestBody TodoRequestDTO request) {

        return ResponseEntity.ok(
                todoUCFactory
                        .saveTodoUseCase()
                        .execute(request.toDomain())
        );
    }
}
