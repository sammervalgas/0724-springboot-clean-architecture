UPDATE TodoEntity t SET t.title = :#{#todo.title}, t.task = :#{#todo.task}, t.priority = :#{#todo.priority} WHERE t.publicId = :pid