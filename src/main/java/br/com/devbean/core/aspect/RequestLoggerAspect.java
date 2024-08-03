package br.com.devbean.core.aspect;

import br.com.devbean.core.annotations.RequestLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.UUID;

@Slf4j
@Aspect
@Component
public class RequestLoggerAspect {

    @Autowired
    private HttpServletRequest httpRequest;

    @Pointcut(value = "@annotation(br.com.devbean.core.annotations.RequestLogger)")
    private void pointcut() { }

    // Executes before and after joinpoint
    @Around("@annotation(requestLogger)")
    public Object intercept(
            ProceedingJoinPoint joinPoint,
            RequestLogger requestLogger
    ) throws Throwable {

        log.info("Intercepting Requests...");

        if ( requestLogger.type() == RequestLoggerType.AROUND) {
            log.info("Request URL Called: {}", httpRequest.getRequestURI());
        }

        log.info("Message Value => {}", requestLogger.description() );

        return joinPoint.proceed();
    }

    @Before("@annotation(requestLogger)")
    public void before(JoinPoint joinPoint, RequestLogger requestLogger) {

        if( requestLogger.type() != RequestLoggerType.BEFORE ) {
            log.info("Skipping execution !!!!");
            return;
        }

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        Object[] args = joinPoint.getArgs();

        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(requestLogger.value().replace(requestLogger.value(), "[0]"));

        UUID pid = expression.getValue(args, UUID.class);

        log.info("ID {} intercepted Before on {}", pid, method.getName());

    }
}
