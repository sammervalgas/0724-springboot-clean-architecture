package br.com.devbean.domain.models;

public class Todo {

    private String title;

    private String task;

    private Integer priority;

    public Todo() {
    }

    public Todo( String title,
                String task,
                Integer priority)
    {
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
}
