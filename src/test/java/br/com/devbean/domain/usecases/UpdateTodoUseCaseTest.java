package br.com.devbean.domain.usecases;


import br.com.devbean.domain.models.Todo;
import br.com.devbean.domain.services.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UpdateTodoUseCaseTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private UpdateTodoUseCase updateTodoUseCase;

    private Todo todo;
    private UUID uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        todo = new Todo("Task 01", "Task for tests", 1);
    }

    @Test
    void testExecute() {
        Todo updatedTodo = new Todo("Updated Task", "Updated task for tests", 2);

        when(todoService.update(uuid, todo)).thenReturn(Optional.of(updatedTodo));

        Optional<Todo> result = updateTodoUseCase.execute(uuid, todo);

        assertTrue(result.isPresent());
        assertEquals("Updated Task", result.get().getTitle());
        assertEquals("Updated task for tests", result.get().getTask());
        assertEquals(2, result.get().getPriority());
        verify(todoService, times(1)).update(uuid, todo);
    }

    @Test
    void testExecuteNotFound() {
        when(todoService.update(uuid, todo)).thenReturn(Optional.empty());

        Optional<Todo> result = updateTodoUseCase.execute(uuid, todo);

        assertFalse(result.isPresent());
        verify(todoService, times(1)).update(uuid, todo);
    }
}