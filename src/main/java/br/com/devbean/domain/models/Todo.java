package br.com.devbean.domain.models;

import java.util.UUID;

/**
 * ALTERADO RECORD PARA CLASSE PORQUE O MODEL MAPPER NAO LIDA BEM
 * COM RECORD E NAO ATUALIZA OS VALORES NO METODO MAP()
 */

public class Todo {

    private String title;
    private String task;
    private Integer priority;
    private UUID publicId;

    public Todo(String title, String task, Integer priority, UUID publicId) {
        this.title = title;
        this.task = task;
        this.priority = priority;
        this.publicId = publicId;
    }

    public Todo(String title, String task, Integer priority) {
        this.title = title;
        this.task = task;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public UUID getPublicId() {
        return publicId;
    }

    public void setPublicId(UUID publicId) {
        this.publicId = publicId;
    }
}

//
//public record Todo(String title, String task, Integer priority, UUID publicId) {
//
//    // Override Construtor Records
//    public  Todo( String title, String task, Integer priority) {
//        this(title, task, priority, UUID.randomUUID());
//    }
//}


