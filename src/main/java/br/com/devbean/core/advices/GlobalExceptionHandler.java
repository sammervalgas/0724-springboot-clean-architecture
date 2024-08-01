package br.com.devbean.core.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleMethodArgumentNotValid(
          MethodArgumentNotValidException ex) {
    Map<String, List<HttpErrorMessage>> body = new HashMap<>();

    List<HttpErrorMessage> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(HttpErrorMessage::convert)
            .collect(Collectors.toList());

    body.put("errors", errors);
    
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }
}

@Data
@Builder
class HttpErrorMessage {

  private String field;
  private String message;
  private String code;

  public static HttpErrorMessage convert(
          FieldError fieldError
  ){
    return HttpErrorMessage
              .builder()
                      .field(fieldError.getField())
                      .message(fieldError.getDefaultMessage())
                      .code(fieldError.getCode())
              .build();
  }

}