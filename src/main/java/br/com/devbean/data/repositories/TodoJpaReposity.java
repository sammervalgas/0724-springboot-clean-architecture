package br.com.devbean.data.repositories;

import br.com.devbean.core.utils.QueryStringUtils;
import br.com.devbean.data.entities.TodoEntity;
import br.com.devbean.domain.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface TodoJpaReposity extends JpaRepository<TodoEntity, Long> {

    Optional<TodoEntity> findByPublicId(UUID ppid);

    @Transactional
    @Modifying
//    @Query("UPDATE TodoEntity t " +
//            "SET t.title    = :#{#todo.title}, " +
//            "    t.task     = :#{#todo.task}, " +
//            "    t.priority = :#{#todo.priority} " +
//            "WHERE t.publicId = :pid")
    @Query(QueryStringUtils.TODO_CLASS.UPDATE_QUERY)
    Integer update(UUID pid, Todo todo);
}
