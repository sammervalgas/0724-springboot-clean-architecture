package br.com.devbean.core.utils;

public class QueryStringUtils {

    public static class TODO_CLASS {

        public static final String UPDATE_QUERY = "UPDATE TodoEntity t SET t.title = :#{#todo.title}, t.task = :#{#todo.task}, t.priority = :#{#todo.priority} WHERE t.publicId = :pid";

    }

}


