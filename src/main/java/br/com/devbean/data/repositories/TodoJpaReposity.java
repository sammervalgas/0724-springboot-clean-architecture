package br.com.devbean.data.repositories;

import br.com.devbean.data.entities.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoJpaReposity extends JpaRepository<TodoEntity, Long> { }
