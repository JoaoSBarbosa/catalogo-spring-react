package br.com.jbcatalog.jbcatalog.controllers.exceptions;

import br.com.jbcatalog.jbcatalog.services.exception.ControllerConflictException;
import br.com.jbcatalog.jbcatalog.services.exception.ControllerNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice //permite que esta classe intercepte alguma exceção que acontecer
public class ControllerExceptionHandler {

    //Adicionando a anotecion @ExceptionHandler(EntityNotFoundException.class) estamos definido e informando para o JPA
    // que sempre que acontecer uma exceção na camada conntroller, ou seja, o service retornando uma exceção para controller
    // e essa excção for do tipo EntityNotFoundException, será essa classe responsavel por trata-la

    @ExceptionHandler(ControllerNotFoundException.class)
    public ResponseEntity<StanderError> entityNotFound(ControllerNotFoundException exception, HttpServletRequest request){

        StanderError standerError = new StanderError();
        standerError.setTimestamp(Instant.now());
        standerError.setStatus(HttpStatus.NOT_FOUND.value());
        standerError.setError("Recurso não encontrado 😣");
        standerError.setMessage(exception.getMessage());
        standerError.setPath(request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standerError);
    }

    @ExceptionHandler(ControllerConflictException.class)
    public ResponseEntity<StanderError> conflictEntity(ControllerConflictException conflictException, HttpServletRequest httpServletRequest){
        StanderError error = new StanderError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setMessage(conflictException.getMessage());
        error.setError("ID já existente");
        error.setPath(httpServletRequest.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

}
