package br.com.devbean.domain.usecases;

import br.com.devbean.domain.models.Todo;
import br.com.devbean.domain.services.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ListTodoUseCaseTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private ListTodoUseCase listTodoUseCase;

    private Todo todo;

    @BeforeEach
    void setUp() {
        todo = new Todo("Task 01", "Task for tests", 1);
    }

    @Test
    void testExecute() {
        when(todoService.listTodos()).thenReturn(Collections.singletonList(todo));

        List<Todo> todos = listTodoUseCase.execute();

        assertNotNull(todos);
        assertEquals(1, todos.size());
        assertEquals("Task 01", todos.get(0).getTitle());
        assertEquals("Task for tests", todos.get(0).getTask());
        assertEquals(1, todos.get(0).getPriority());
        verify(todoService, times(1)).listTodos();
    }
}