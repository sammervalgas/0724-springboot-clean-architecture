package br.com.devbean.core.contexts;

import br.com.devbean.core.configs.ObjectMapperConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppBeanContext {

    public static ObjectMapper getObjectMapper() {
        ApplicationContext context = new AnnotationConfigApplicationContext(ObjectMapperConfig.class);
        return context.getBean(ObjectMapper.class);
    }

}
