package br.com.devbean.core.configs;

import br.com.devbean.data.repositories.TodoJpaReposity;
import br.com.devbean.data.services.TodoServiceImpl;
import br.com.devbean.domain.services.TodoService;
import br.com.devbean.domain.usecases.TodoUCFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dependency Invertion
 */
@Configuration
public class TodoConfig {

    @Bean
    TodoServiceImpl todoService(final TodoJpaReposity jpaReposity) {
        return new TodoServiceImpl(jpaReposity);
    }

   @Bean
   TodoUCFactory todoUCFactory(final TodoService service) {
       return new TodoUCFactory(service);
   }

}
