package br.com.devbean.data.services;

import br.com.devbean.data.entities.TodoEntity;
import br.com.devbean.data.entities.mapper.TodoEntityMapper;
import br.com.devbean.data.repositories.TodoJpaReposity;
import br.com.devbean.domain.models.Todo;
import br.com.devbean.domain.services.TodoService;

import java.util.List;
import java.util.stream.Collectors;

public class TodoServiceImpl implements TodoService {

    private final TodoJpaReposity todoJpaReposity;

    public TodoServiceImpl(TodoJpaReposity todoJpaReposity) {
        this.todoJpaReposity = todoJpaReposity;
    }

    @Override
    public List<Todo> listTodos() {

        return todoJpaReposity.findAll()
                .stream()
                .map(TodoEntityMapper::toDomain)
                .collect(Collectors.toList());

    }

    @Override
    public Todo save(Todo todo) {
        TodoEntity entity = TodoEntityMapper.toEntity(todo);

        todoJpaReposity.save(entity);

        return TodoEntityMapper.toDomain(entity);
    }
}
