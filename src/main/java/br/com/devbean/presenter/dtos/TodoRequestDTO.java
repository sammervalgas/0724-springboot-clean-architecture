package br.com.devbean.presenter.dtos;

import br.com.devbean.domain.models.Todo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoRequestDTO {

    private String title;
    private String task;
    private Integer priority;

    public Todo toDomain() {
        return new Todo(
                this.title,
                this.task,
                this.priority
        );
    }
}
