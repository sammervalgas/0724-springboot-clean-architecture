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
public class FindTodoUseCaseTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private FindTodoUseCase findTodoUseCase;

    private Todo todo;
    private UUID uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
        todo = new Todo("Task 01", "Task for tests", 1);
    }

    @Test
    void testExecute() {
        when(todoService.findByPublicId(uuid)).thenReturn(Optional.of(todo));

        Optional<Todo> foundTodo = findTodoUseCase.execute(uuid);

        assertTrue(foundTodo.isPresent());
        assertEquals("Task 01", foundTodo.get().getTitle());
        assertEquals("Task for tests", foundTodo.get().getTask());
        assertEquals(1, foundTodo.get().getPriority());
        verify(todoService, times(1)).findByPublicId(uuid);
    }

    @Test
    void testExecuteNotFound() {
        when(todoService.findByPublicId(uuid)).thenReturn(Optional.empty());

        Optional<Todo> foundTodo = findTodoUseCase.execute(uuid);

        assertFalse(foundTodo.isPresent());
        verify(todoService, times(1)).findByPublicId(uuid);
    }
}