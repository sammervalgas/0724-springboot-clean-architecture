package br.com.devbean.presenter.controllers;

import br.com.devbean.domain.models.Todo;
import br.com.devbean.domain.usecases.TodoUseCaseFactory;
import br.com.devbean.presenter.dtos.TodoRequestDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isA;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TodoControllerTest {

    private static final String BASE_URL = "/todos";
    private ObjectMapper objectMapper;
    private Todo createdTodo;

    @Autowired
    private TodoUseCaseFactory todoUseCaseFactory;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        // Cria um Todo para ser usado nos testes
        createdTodo = new Todo("Task 01", "Task for tests", 1, UUID.randomUUID());
    }

    @Test
    void testListTodos() {
        given()
                .port(port)
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("$", isA(List.class));
    }

    @Test
    void testSaveTodo() throws JsonProcessingException {
        given().port(port)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(objectMapper.writeValueAsString(
                        new TodoRequestDTO(
                                createdTodo.getTitle(),
                                createdTodo.getTask(),
                                createdTodo.getPriority())))
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("title", equalTo("Task 01"))
                .body("task", equalTo("Task for tests"))
                .body("priority", equalTo(1));
    }

    @Test
    void testFindTodo() {
        // Primeiro, salve o Todo para ter um ID válido
        UUID pid = todoUseCaseFactory.saveTodo().execute(createdTodo).getPublicId();

        given()
                .port(port)
                .pathParam("pid", pid)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(BASE_URL + "/{pid}")
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("title", equalTo("Task 01"))
                .body("task", equalTo("Task for tests"))
                .body("priority", equalTo(1));

        todoUseCaseFactory.deleteTodo().execute(pid);

    }

    @Test
    void testUpdateTodo() throws JsonProcessingException {
        // Primeiro, salve o Todo para ter um ID válido
        UUID pid = todoUseCaseFactory.saveTodo().execute(createdTodo).getPublicId();

        Todo updatedTodo = new Todo("Updated Task", "Updated task description", 2);

        given()
                .port(port)
                .queryParam("pid", pid)
                .contentType(ContentType.JSON)
                .body(objectMapper.writeValueAsString(new TodoRequestDTO(
                        updatedTodo.getTitle(),
                        updatedTodo.getTask(),
                        updatedTodo.getPriority())))
                .when()
                .put(BASE_URL)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("title", equalTo("Updated Task"))
                .body("task", equalTo("Updated task description"))
                .body("priority", equalTo(2));

        todoUseCaseFactory.deleteTodo().execute(pid);
    }

    @Test
    void testDeleteTodo() {
        // Primeiro, salve o Todo para ter um ID válido
        UUID pid = todoUseCaseFactory.saveTodo().execute(createdTodo).getPublicId();

        given()
                .port(port)
                .queryParam("pid", pid)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .delete(BASE_URL)
                .then()
                .statusCode(HttpStatus.NO_CONTENT.value());
    }
}