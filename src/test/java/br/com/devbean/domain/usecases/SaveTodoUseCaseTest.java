package br.com.devbean.domain.usecases;

import br.com.devbean.domain.models.Todo;
import br.com.devbean.domain.services.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveTodoUseCaseTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private SaveTodoUseCase saveTodoUseCase;

    private Todo todo;

    @BeforeEach
    void setUp() {
        todo = new Todo("Task 01", "Task for tests", 1);
    }

    @Test
    void testExecute() {
        when(todoService.save(todo)).thenReturn(todo);

        Todo savedTodo = saveTodoUseCase.execute(todo);

        assertNotNull(savedTodo);
        assertEquals("Task 01", savedTodo.getTitle());
        assertEquals("Task for tests", savedTodo.getTask());
        assertEquals(1, savedTodo.getPriority());
        verify(todoService, times(1)).save(todo);
    }
}