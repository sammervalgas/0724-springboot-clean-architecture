package br.com.devbean.domain.usecases;

import br.com.devbean.domain.services.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeleteTodoUseCaseTest {

    @Mock
    private TodoService todoService;

    @InjectMocks
    private DeleteTodoUseCase deleteTodoUseCase;

    private UUID uuid;

    @BeforeEach
    void setUp() {
        uuid = UUID.randomUUID();
    }

    @Test
    void testExecute() {
        // Executa o caso de uso de deletar
        deleteTodoUseCase.execute(uuid);

        // Verifica se o método delete foi chamado exatamente uma vez com o UUID correto
        verify(todoService, times(1)).delete(uuid);
    }

    @Test
    void testExecuteWithException() {
        // Configura o mock para lançar uma exceção quando o método delete for chamado
        doThrow(new RuntimeException("Deletion failed")).when(todoService).delete(uuid);

        // Verifica se a exceção é lançada ao executar o caso de uso
        assertThrows(RuntimeException.class, () -> deleteTodoUseCase.execute(uuid));

        // Verifica se o método delete foi chamado exatamente uma vez com o UUID correto
        verify(todoService, times(1)).delete(uuid);
    }
}