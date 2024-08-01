package br.com.devbean.presenter.controllers;

import br.com.devbean.core.annotations.RequestLogger;
import br.com.devbean.core.aspect.RequestLoggerType;
import br.com.devbean.domain.models.Todo;
import br.com.devbean.domain.usecases.TodoUseCaseFactory;
import br.com.devbean.presenter.dtos.TodoRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoUseCaseFactory todoUseCaseFactory;

    @RequestLogger(type = RequestLoggerType.AROUND, description = "Get Mapping List ToDos")
    @GetMapping
    public ResponseEntity<List<Todo>> list() {
        return ResponseEntity.ok(
                todoUseCaseFactory
                        .listTodos()
                        .execute()
        );
    }

    @RequestLogger(type = RequestLoggerType.BEFORE, value = "{pid}")
    @GetMapping("/{pid}")
    public ResponseEntity<Todo> findByPublicId(@PathVariable(name = "pid") UUID pid) {
        return this.todoUseCaseFactory
                .findTodo()
                .execute(pid)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> saveTodo(@Valid @RequestBody TodoRequestDTO request) {
        return new ResponseEntity<>(
                todoUseCaseFactory
                        .saveTodo()
                        .execute(request.toDomain()),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam UUID pid) {
        todoUseCaseFactory
                .deleteTodo()
                .execute(pid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Todo> update(@RequestParam UUID pid, @Valid @RequestBody TodoRequestDTO request) {
    return todoUseCaseFactory
                .updateTodo()
                .execute(pid, request.toDomain())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
