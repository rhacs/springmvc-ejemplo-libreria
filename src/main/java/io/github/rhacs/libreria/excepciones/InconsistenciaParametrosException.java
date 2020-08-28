package io.github.rhacs.libreria.excepciones;

import io.github.rhacs.libreria.modelos.ErrorResponse;

public class InconsistenciaParametrosException extends RuntimeException {

    // Constantes
    // -----------------------------------------------------------------------------------------

    /**
     * Número de serie de la clase
     */
    private static final long serialVersionUID = 1531940198276472548L;

    // Atributos
    // -----------------------------------------------------------------------------------------

    /**
     * Objeto {@link ErrorResponse} que contiene la información de la excepción
     */
    private final transient ErrorResponse response;

    // Constructores
    // -----------------------------------------------------------------------------------------

    /**
     * Crea una nueva instancia de la excepción
     * {@link InconsistenciaParametrosException}
     * 
     * @param response objeto {@link ErrorResponse} que contiene la información de
     *                 la excepción
     */
    public InconsistenciaParametrosException(ErrorResponse response) {
        super(response.getMessage());
        this.response = response;
    }

    // Getters
    // -----------------------------------------------------------------------------------------

    /**
     * @return la {@link ErrorResponse}
     */
    public ErrorResponse getResponse() {
        return response;
    }

}
