package br.com.devbean.data.services;

import br.com.devbean.domain.models.Todo;
import br.com.devbean.domain.services.TodoService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TodoServiceImpl implements TodoService {

    @Override
    public List<Todo> listTodos() {
        return List.of(
                new Todo("TODO 1", "TASK 1", 1),
                new Todo("TODO 2", "TASK FOR LIFE", 3)
        );
    }
}
