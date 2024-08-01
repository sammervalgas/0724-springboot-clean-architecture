package br.com.devbean.core.annotations;

import br.com.devbean.core.aspect.RequestLoggerType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestLogger {

    String value() default "";

    String description() default "";

    RequestLoggerType type() default RequestLoggerType.AROUND;

    RequestLoggerType[] types() default { RequestLoggerType.AROUND };

}
