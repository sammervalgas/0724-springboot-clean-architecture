package br.com.devbean.core.aspect;


import br.com.devbean.core.annotations.RequestLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Method;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RequestLoggerAspectTest {

    @InjectMocks
    private RequestLoggerAspect requestLoggerAspect;

    @Mock
    private HttpServletRequest httpRequest;

    @Mock
    private ProceedingJoinPoint joinPoint;

    @Mock
    private RequestLogger requestLogger;

    @BeforeEach
    void setUp() { }

    @Test
    void testInterceptWithAroundType() throws Throwable {

        when(requestLogger.type()).thenReturn(RequestLoggerType.AROUND);
        when(requestLogger.description()).thenReturn("Test description");

        // Chama o método intercept
        requestLoggerAspect.intercept(joinPoint, requestLogger);

        // Verifica se os logs foram chamados corretamente
        // É necessário usar um framework de logging como LogCaptor ou outra técnica para verificar os logs

        // Verifica se o proceed foi chamado
        verify(joinPoint, times(1)).proceed();
    }

    @SneakyThrows
    @Test
    void testBeforeWithBeforeType() {
        // Configura o joinPoint mock
        Method method = mock(Method.class);
        when(method.getName()).thenReturn("testMethod");

        MethodSignature signature = mock(MethodSignature.class);
        when(signature.getMethod()).thenReturn(method);

        when(joinPoint.getSignature()).thenReturn(signature);

        when(requestLogger.type()).thenReturn(RequestLoggerType.BEFORE);
        when(requestLogger.value()).thenReturn("[0]");

        when(joinPoint.getArgs()).thenReturn(new Object[]{UUID.randomUUID()});

        // Chama o método before
        requestLoggerAspect.before(joinPoint, requestLogger);

        // Verifica se os logs foram chamados corretamente
        // É necessário usar um framework de logging como LogCaptor ou outra técnica para verificar os logs

        // Verifica se o joinPoint não chamou proceed
        verify(joinPoint, never()).proceed();
    }

    @SneakyThrows
    @Test
    void testBeforeWithNonBeforeType() {

        when(requestLogger.type()).thenReturn(RequestLoggerType.AROUND);

        // Chama o método before
        requestLoggerAspect.before(joinPoint, requestLogger);

        // Verifica se os logs foram chamados corretamente
        // É necessário usar um framework de logging como LogCaptor ou outra técnica para verificar os logs

        // Verifica se o joinPoint não chamou proceed
        verify(joinPoint, never()).proceed();
    }
}