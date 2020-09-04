package io.github.rhacs.libreria;

import org.springframework.http.HttpStatus;

import io.github.rhacs.libreria.modelos.ErrorResponse;

public class Metodos {

    /**
     * Crea una nueva instancia de {@link ErrorResponse} para ser entregada en una
     * excepción
     * 
     * @param atributo       nombre del atributo donde ocurrió el error
     * @param mensaje        detalle del error
     * @param nombreObjeto   nombre del objeto donde ocurrió el error
     * @param valorRechazado valor rechazado por el sistema
     * @param estado         estado http de la respuesta
     * @return un objeto {@link ErrorResponse}
     */
    public static ErrorResponse prepararError(String atributo, String mensaje, String nombreObjeto,
            Object valorRechazado, HttpStatus estado) {
        // Crear nueva instancia de ErrorResponse
        ErrorResponse respuesta = new ErrorResponse(estado.value(), mensaje);

        // Agregar detalle de error
        respuesta.agregarError(atributo, mensaje, nombreObjeto, valorRechazado);

        // Devolver respuesta
        return respuesta;
    }

}
