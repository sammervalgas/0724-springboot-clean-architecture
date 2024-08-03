package br.com.devbean.data.services;

import br.com.devbean.data.entities.TodoEntity;
import br.com.devbean.data.entities.mapper.TodoEntityMapper;
import br.com.devbean.data.repositories.TodoJpaReposity;
import br.com.devbean.domain.models.Todo;
import br.com.devbean.domain.services.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceImplTest {

    @Mock
    private TodoJpaReposity todoJpaReposity;

    @InjectMocks
    private TodoServiceImpl todoService;

    @Captor
    private ArgumentCaptor<TodoEntity> todoEntityCaptor;

    private Todo todo;
    private TodoEntity todoEntity;
    private UUID uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        todo = new Todo("Task 01", "Task for tests", 1);
        todoEntity = TodoEntityMapper.toEntity(todo);
    }

    @Test
    void testListTodos() {
        when(todoJpaReposity.findAll()).thenReturn(Collections.singletonList(todoEntity));

        List<Todo> todos = todoService.listTodos();

        assertNotNull(todos);
        assertEquals(1, todos.size());
        assertEquals(todo.getTitle(), todos.get(0).getTitle());
    }

    @Test
    void testSave() {
        when(todoJpaReposity.save(any(TodoEntity.class))).thenReturn(todoEntity);

        Todo savedTodo = todoService.save(todo);

        verify(todoJpaReposity, times(1)).save(todoEntityCaptor.capture());
        TodoEntity capturedEntity = todoEntityCaptor.getValue();

        assertNotNull(savedTodo);
        assertEquals(todo.getTitle(), savedTodo.getTitle());
        assertEquals(todo.getTitle(), capturedEntity.getTitle());
    }

    @Test
    void testUpdate() {
        Todo updatedTodo = new Todo("Updated","Todo updated data",2);
        updatedTodo.setTitle("Updated Todo");

        when(todoJpaReposity.findByPublicId(uuid)).thenReturn(Optional.of(todoEntity));
        when(todoJpaReposity.update(eq(uuid), any())).thenReturn(1);

        Optional<Todo> result = todoService.update(uuid, updatedTodo);

        assertTrue(result.isPresent());
        assertEquals("Updated Todo", result.get().getTitle());
    }

    @Test
    void testFindByPublicId() {
        when(todoJpaReposity.findByPublicId(uuid)).thenReturn(Optional.of(todoEntity));

        Optional<Todo> foundTodo = todoService.findByPublicId(uuid);

        assertTrue(foundTodo.isPresent());
        assertEquals(todo.getTitle(), foundTodo.get().getTitle());
    }

    @Test
    void testDelete() {
        when(todoJpaReposity.findByPublicId(uuid)).thenReturn(Optional.of(todoEntity));
        doNothing().when(todoJpaReposity).delete(any(TodoEntity.class));

        todoService.delete(uuid);

        verify(todoJpaReposity, times(1)).delete(todoEntityCaptor.capture());
        TodoEntity capturedEntity = todoEntityCaptor.getValue();

        assertEquals(todoEntity, capturedEntity);
    }
}