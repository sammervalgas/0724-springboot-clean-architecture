package br.com.devbean.core.configs;

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
   TodoUCFactory todoUCFactory(
            final TodoService service
   ) {
       return new TodoUCFactory(service);
   }
}
