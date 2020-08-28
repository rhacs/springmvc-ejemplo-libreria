package io.github.rhacs.libreria.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.rhacs.libreria.excepciones.ElementoNoExisteException;
import io.github.rhacs.libreria.modelos.ErrorResponse;

@RestControllerAdvice(basePackages = "io.github.rhacs.libreria.api")
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ElementoNoExisteException.class })
    protected ResponseEntity<ErrorResponse> handleElementoNoExiste(ElementoNoExisteException e) {
        return ResponseEntity.status(e.getErrorResponse().getStatusCode()).body(e.getErrorResponse());
    }

}
