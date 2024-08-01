package br.com.devbean.presenter.dtos;

import br.com.devbean.domain.models.Todo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TodoRequestDTO {

    @NotNull
    @NotEmpty(message = "Campo 'titulo' não pode ser vazio")
    @JsonProperty(value = "titulo", required = true)
    private String title;

    @NotNull
    @Size(min = 2, max = 100, message = "Tarefa deve conter entre 2 a 100 caracteres")
    @JsonProperty(value = "tarefa", required = true)
    private String task;

    @JsonProperty(value = "prioridade", required = true)
    private Integer priority;

    public Todo toDomain() {
        return new Todo(
                this.title,
                this.task,
                this.priority
        );
    }
}
