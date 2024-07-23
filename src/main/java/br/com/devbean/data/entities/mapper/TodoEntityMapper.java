package br.com.devbean.data.entities.mapper;

import br.com.devbean.data.entities.TodoEntity;
import br.com.devbean.domain.models.Todo;

public class TodoEntityMapper {

    public static TodoEntity toEntity(Todo todo) {
        return new TodoEntity(
                todo.title(),
                todo.task(),
                todo.priority()
        );
    }

    public static Todo toDomain(TodoEntity entity) {
        return new Todo(
                entity.getTitle(),
                entity.getTask(),
                entity.getPriority()
        );
    }

}
