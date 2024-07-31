package br.com.devbean.data.services;

import br.com.devbean.data.entities.TodoEntity;
import br.com.devbean.data.entities.mapper.TodoEntityMapper;
import br.com.devbean.data.repositories.TodoJpaReposity;
import br.com.devbean.domain.models.Todo;
import br.com.devbean.domain.services.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    @Transactional
    @Override
    public Optional<Todo> update(UUID pid, Todo current) {

        Optional<Todo> baseTodo = this.findByPublicId(pid);

        if(baseTodo.isPresent()){
            Todo todo = baseTodo.get();

            ModelMapper modelMapper = new ModelMapper();

            // Configura ModelMaper para ignorar nulos
            modelMapper.getConfiguration().setSkipNullEnabled( true );

            // Mapeia os campos de current para referencia de `tod0` e onde for null
            modelMapper.map( current, todo );

            int updated = this.todoJpaReposity.update( pid, todo );
            if(updated > 0) {
                return Optional.of(todo);
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Todo> findByPublicId(UUID pid) {
        Optional<TodoEntity> todoEntityOptional = this.todoJpaReposity.findByPublicId(pid);
        return todoEntityOptional.map(TodoEntityMapper::toDomain);
    }

    @Override
    public void delete(UUID pid) {
        Optional<TodoEntity> todoEntityOptional = todoJpaReposity.findByPublicId(pid);
        todoEntityOptional.ifPresent(todoJpaReposity::delete);
    }
}
