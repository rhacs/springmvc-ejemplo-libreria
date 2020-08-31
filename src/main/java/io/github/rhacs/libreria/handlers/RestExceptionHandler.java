package io.github.rhacs.libreria.handlers;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import io.github.rhacs.libreria.excepciones.ElementoNoExisteException;
import io.github.rhacs.libreria.excepciones.InconsistenciaParametrosException;
import io.github.rhacs.libreria.excepciones.ViolacionRestriccionUnicaException;
import io.github.rhacs.libreria.modelos.ErrorResponse;

@RestControllerAdvice(basePackages = "io.github.rhacs.libreria.api")
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Maneja las excepciones {@link ElementoNoExisteException}
     * 
     * @param e objeto {@link ElementoNoExisteException} que contiene la información
     *          del error
     * @return un objeto {@link ResponseEntity} con la respuesta a la solicitud
     */
    @ExceptionHandler(value = { ElementoNoExisteException.class })
    protected ResponseEntity<ErrorResponse> handleElementoNoExiste(ElementoNoExisteException e) {
        return ResponseEntity.status(e.getErrorResponse().getStatusCode()).body(e.getErrorResponse());
    }

    /**
     * Maneja las excepciones {@link ViolacionRestriccionUnicaException}
     * 
     * @param e objeto {@link ViolacionRestriccionUnicaException} que contiene la
     *          información de la excepción
     * @return un objeto {@link ResponseEntity} con la respuesta a la solicitud
     */
    @ExceptionHandler(value = { ViolacionRestriccionUnicaException.class })
    protected ResponseEntity<ErrorResponse> handleViolacionRestriccionUnica(ViolacionRestriccionUnicaException e) {
        return ResponseEntity.status(e.getResponse().getStatusCode()).body(e.getResponse());
    }

    /**
     * Maneja las excepciones {@link InconsistenciaParametrosException}
     * 
     * @param e objeto {@link InconsistenciaParametrosException} que contiene la
     *          información de la excepción
     * @return un objeto {@link ResponseEntity} con la respuesta a la solicitud
     */
    @ExceptionHandler(value = { InconsistenciaParametrosException.class })
    protected ResponseEntity<ErrorResponse> handleInconsistenciaParametros(InconsistenciaParametrosException e) {
        return ResponseEntity.status(e.getResponse().getStatusCode()).body(e.getResponse());
    }

    /**
     * Maneja las excepciones de validación {@link ConstraintViolationException}
     * 
     * @param e objeto {@link ConstraintViolationException} que contiene la
     *          información de la excepción
     * @return un objeto {@link ResponseEntity} con la respuesta a la solicitud
     */
    @ExceptionHandler(value = { ConstraintViolationException.class })
    protected ResponseEntity<ErrorResponse> handleConstraintViolation(ConstraintViolationException e) {
        // Estado HTTP
        HttpStatus status = HttpStatus.BAD_REQUEST;

        // Crear respuesta
        ErrorResponse response = new ErrorResponse();
        response.setStatusCode(status.value());
        response.setMessage("Error de validación");

        // Agregar errores de validación
        e.getConstraintViolations().forEach(response::agregarError);

        // Enviar respuesta
        return ResponseEntity.status(status).body(response);
    }

}
