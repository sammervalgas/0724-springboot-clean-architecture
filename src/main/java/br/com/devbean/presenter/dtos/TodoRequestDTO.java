package br.com.devbean.presenter.dtos;

import br.com.devbean.domain.models.Todo;

public class TodoRequestDTO {

    private final String title;
    private final String task;
    private final Integer priority;

    public TodoRequestDTO(
            String title,
            String task,
            Integer priority
    ) {
        this.title = title;
        this.task = task;
        this.priority = priority;
    }

    public Todo toDomain() {
        return new Todo(
                this.title,
                this.task,
                this.priority
        );
    }
}
